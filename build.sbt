name := "sbt-multi-project-cached-resolution-failure"

scalaVersion := "2.12.6"

lazy val root = (project in file(".")).dependsOn(main).aggregate(main, lib)

lazy val main = project.settings(commonSettings:_*).dependsOn(lib)

lazy val lib = project.settings(Seq(libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.7") ++ commonSettings:_*)

lazy val commonSettings = Seq[sbt.Def.SettingsDefinition](
  // Project builds successfully if these two lines are commented out:
  updateOptions := updateOptions.value.withCachedResolution(true),
  updateConfiguration := updateConfiguration.value.withMetadataDirectory(Some(file(".") / "target" / "resolution" / "cache"))
)