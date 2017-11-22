# Monitoring Setup

run the project and check `jconsole` for jmx stats
it will ship to datadog if the datadog agent is installed
and you can microbench individual functions


run the project and ship metrics
```bash
sbt server/run
```


microbench the multiplication function
```bash
sbt benchServer/jmh:run
```

benchmarking also supports java flight recorder
https://docs.oracle.com/javacomponents/jmc-5-4/jfr-runtime-guide/about.htm#JFRUH170