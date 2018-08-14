This reproduces https://github.com/sbt/sbt/issues/3761

This multi-module project fails to build when cached dependency resolution is enabled using SBT 1.2.1:

```
updateOptions := updateOptions.value.withCachedResolution(true),
updateConfiguration := updateConfiguration.value.withMetadataDirectory(Some(file(".") / "target" / "resolution" / "cache"))
```

Here is the compile failure:

```
sbt:sbt-multi-project-cached-resolution-failure> compile
[info] Updating lib...
[info] Done updating.
[info] Updating main...
[info] Compiling 1 Scala source to /Users/tim/fm/sbt-multi-project-cached-resolution-failure/lib/target/scala-2.12/classes ...
[info] Done updating.
[info] Updating ...
[info] Done updating.
[info] Done compiling.
[info] Compiling 1 Scala source to /Users/tim/fm/sbt-multi-project-cached-resolution-failure/main/target/scala-2.12/classes ...
[error] /Users/tim/fm/sbt-multi-project-cached-resolution-failure/main/src/main/scala/Main.scala:5:17: object apache is not a member of package org
[error]     println(org.apache.commons.lang3.StringUtils.capitalize("foo"))
[error]                 ^
[error] one error found
[error] (main / Compile / compileIncremental) Compilation failed
[error] Total time: 1 s, completed Aug 14, 2018 8:12:54 AM
```

If cached resolution is disabled then the project buils fine:

```
sbt:sbt-multi-project-cached-resolution-failure> compile
[info] Updating lib...
[info] Done updating.
[info] Updating main...
[info] Compiling 1 Scala source to /Users/tim/fm/sbt-multi-project-cached-resolution-failure/lib/target/scala-2.12/classes ...
[info] Done updating.
[info] Updating ...
[info] Done updating.
[info] Done compiling.
[info] Compiling 1 Scala source to /Users/tim/fm/sbt-multi-project-cached-resolution-failure/main/target/scala-2.12/classes ...
[info] Done compiling.
[success] Total time: 1 s, completed Aug 14, 2018 8:12:27 AM
```