# default-of-credit-card-clients-svm
Using svm (libsvm) to predit default of credit card clients 

Library:

jxl.jar -- java excel api, for reading .xls files

libsvm.jar -- svm package 

.java files:

jmain.java -- data compression, training, testing (using libsvm api)

CompressData.java -- only for data compression, import svm_scale

DataSet.java -- .xls -> .txt conversion. Considering that plotroc requires that the labels should be {-1,1}, 0 -> -1 here.

svm_scale.java -- from libsvm/java


