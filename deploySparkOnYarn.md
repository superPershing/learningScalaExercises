# 将Yarn部署在Hadoop Yarn上
## 集群配置
所有节点的`username`为`spark-test`,master节点为`dc001`, slave节点为`dc002,dc003...`
修改每个节点的`/etc/hosts`文件：  
```
10.20.51.154 dc001
10.20.42.194 dc002
10.20.42.177 dc003 
```
将Authentication Key-pairs分发给所有的节点，方便master节点使用ssh连接到所有的节点来管理集群。  
在master节点上：
```
ssh-keygen -b 4096
ssh-copy-id -i $HOME/.ssh/id_rsa.pub spark-test@dc001
ssh-copy-id -i $HOME/.ssh/id_rsa.pub spark-test@dc002
ssh-copy-id -i $HOME/.ssh/id_rsa.pub spark-test@dc003
```
## Hadoop相关配置
### 安装二进制的Hadoop:
在master节点上
```
wget https://archive.apache.org/dist/hadoop/core/hadoop-2.7.3/hadoop-2.7.3.tar.gz
tar -xzf hadoop-2.7.3.tar.gz
```
### 将Hadoop的基本命令添加到环境变量中
修改master节点的`.profile`文件，向其中添加:
```
PATH=/home/spark-test/hadoop-2.7.3/bin:/home/spark-test/hadoop-2.7.3/sbin:$PATH
```
#### 设置hadoop java环境变量
修改`~/hadoop-2.7.3/etc/hadoop/hadoop-env.sh`文件：  
```
export JAVA_HOME=${JAVA_HOME}
```
如果系统尚未配置了`JAVA_HOME`环境变量，则将`${JAVA_HOME}`修改为`jre`的目录。  
#### 设置主节点NameNode的地址
修改`~/hadoop-2.7.3/etc/hadoop/core-site.xml`，注意将其中的`dc001`修改为你自己的master节点的name(见`/etc/hosts`文件)
```
<configuration>
    <property>
        <name>fs.default.name</name>
        <value>hdfs://dc001:9000</value>
    </property>
</configuration>
```
#### 为HDFS设置路径
修改`~/hadoop-2.7.3/etc/hadoop/hdfs-site.xml`，注意将其中的`spark-test`修改为你自己的机器用户名
```
<configuration>
    <property>
            <name>dfs.namenode.name.dir</name>
            <value>/home/spark-test/data/nameNode</value>
    </property>

    <property>
            <name>dfs.datanode.data.dir</name>
            <value>/home/spark-test/data/dataNode</value>
    </property>

    <property>
            <name>dfs.replication</name>
            <value>1</value>
    </property>
</configuration>
```
`dfs.replication`指所有的数据在集群上被复制的次数，最好不要超出slave节点数量。  

#### 修改Yarn相关配置
在`~/hadoop-2.7.3/etc/hadoop/`中，将`mapred-site.xml.template`重命名为`mapred-site.xml`  
```
cd ~/hadoop-2.7.3/etc/hadoop/
mv mapred-site.xml.template mapred-site.xml
```
修改`mapred-site.xml`文件：
```
<configuration>
    <property>
            <name>mapreduce.framework.name</name>
            <value>yarn</value>
    </property>
</configuration>
```
修改`yarn-site.xml`文件，注意将其中的`dc001`修改为你自己的master节点的name(见`/etc/hosts`文件)
```
<configuration>
    <property>
            <name>yarn.acl.enable</name>
            <value>0</value>
    </property>

    <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>dc001</value>
    </property>

    <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
    </property>
</configuration>
```
#### 配置slaves/workers
修改`~/hadoop-2.7.3/etc/hadoop/slaves`（高版本hadoop应为workers文件）  
```
dc002
dc003
```

#### 在每个节点上复制配置文件
```
scp hadoop-*.tar.gz dc002:/home/spark-test
scp hadoop-*.tar.gz dc003:/home/spark-test
```
在每台slave机器上
```
tar -xzf hadoop-2.7.3.tar.gz
```
在master节点上,运行shell:
```
for node in dc002 dc003; do
    scp ~/hadoop-2.7.3/etc/hadoop/* $node:/home/spark-test/hadoop-2.7.3/etc/hadoop/;
done
```
#### 格式化HDFS
在master节点上
```
hdfs namenode -format
```

#### HDFS相关命令
启动hdfs
```
start-dfs.sh
```
运行jps(Java Virtual Machine Process Status Tool)命令查看节点进程状态：
```
jps
```
在master节点应类似
```
21922 Jps
21603 NameNode
21787 SecondaryNameNode
```
在slave节点上应类似
```
19728 DataNode
19819 Jps
```
关闭hdfs
```
stop-dfs.sh
```

#### 监控hdfs状态
`master-IP`是master节点的ip地址
```
http://master-IP:50070
```

#### Yarn命令
启动yarn
```
start-yarn.sh
```
关闭yarn
```
stop-yarn.sh
```
监控Yarn状态,打印节点和应用状态
```
yarn node -list
yarn application -list
```
在浏览器查看
```
http://master-IP:8088
```

### 安装和配置spark
```
wget https://mirrors.tuna.tsinghua.edu.cn/apache/spark/spark-2.3.2/spark-2.3.2-bin-hadoop2.7.tgz
tar -xvf spark-2.3.2-bin-hadoop2.7.tgz
```
将spark命令添加到环境变量
修改`.profile`文件，添加
```
PATH=/home/spark-test/spark-2.3.2/bin:$PATH
```
#### 集成spark和yarn
修改`.profile`文件
```
export HADOOP_CONF_DIR=/home/spark-test/hadoop-2.7.3/etc/hadoop
export SPARK_HOME=/home/spark-test/spark-2.3.2
export LD_LIBRARY_PATH=/home/spark-test/hadoop-2.7.3/lib/native:$LD_LIBRARY_PATH
```
修改spark配置文件`spark-defaults.conf`
```
cd ~/spark-test/spark-2.3.2/conf/
mv spark-defaults.conf.template spark-defaults.conf
```
修改`spark-defaults.conf`
```
spark.master    yarn
```

#### 监控spark程序
修改`~/spark-test/spark-2.3.2/conf/spark-defaults.conf`文件，添加
```
spark.eventLog.enabled  true
spark.eventLog.dir hdfs://node-master:9000/spark-logs
spark.history.provider            org.apache.spark.deploy.history.FsHistoryProvider
spark.history.fs.logDirectory     hdfs://node-master:9000/spark-logs
spark.history.fs.update.interval  10s
spark.history.ui.port             18080
```
在HDFS中创建log文件夹和启动历史记录Server
```
hdfs dfs -mkdir /spark-logs
~/spark-test/spark-2.3.2/sbin/start-history-server.sh
```

#### 提交spark测试任务：
```
./spark-submit --deploy-mode client --class org.apache.spark.examples.SparkPi spark-2.3.2-bin-hadoop2.7/examples/jars/spark-examples_2.11-2.3.2.jar 10
```
如果出现
```
ERROR TransportClient:233 - Failed to send RPC 4858956348523471318 to /10.20.42.194:54288: java.nio.channels.ClosedChannelException
...
ERROR YarnScheduler:70 - Lost executor 1 on dc002: Slave lost
```
类似错误，则修改`yarn-site.xml`文件(注意所有的节点都要修改)：
```
<property>
    <name>yarn.nodemanager.pmem-check-enabled</name>
    <value>false</value>
</property>

<property>
    <name>yarn.nodemanager.vmem-check-enabled</name>
    <value>false</value>
</property>
```

### Web UI汇总
 - http://master-IP:18080/  Spark History Server
 - http://master-IP:50070/  Hadoop Overview
 - http://master-IP:8088/cluster  Hadoop Cluster Information


参考资料:  
 - https://hadoop.apache.org/
 - https://spark.apache.org/
 - https://spark.apache.org/docs/2.3.2/running-on-yarn.html
 - https://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-site/YARN.html
 - https://www.linode.com/docs/databases/hadoop/how-to-install-and-set-up-hadoop-cluster/
 - https://www.linode.com/docs/databases/hadoop/install-configure-run-spark-on-top-of-hadoop-yarn-cluster/
 - https://www.cnblogs.com/pojishou/p/6358588.html