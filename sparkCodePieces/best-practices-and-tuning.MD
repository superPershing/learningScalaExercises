1. 不要对大的RDDs使用collect
当对RDD实行collect操作时，整个数据都会被拷贝至驱动器，也就是master节点。