dependencies {
    api(project(":graphstream-gs-core"))
    api("org.apache.commons:commons-math3:_")
    api("org.jfree:jfreechart:_")
}

publishOnCentral {
    projectDescription =
        "The GraphStream library. With GraphStream you deal with" +
        "graphs. Static and Dynamic. You create them from scratch, from a file" +
        "or any source. You display and render them. This package contains algorithms and generators."
    projectLongName = "gs-algo"
}
