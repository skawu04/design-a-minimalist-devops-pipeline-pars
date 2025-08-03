// dqpc_design_a_minima.kt

import java.io.File

data class PipelineConfig(val stages: List<Stage>)

data class Stage(val name: String, val tasks: List<Task>)

sealed class Task {
    data class CommandTask(val command: String) : Task()
    data class ScriptTask(val script: File) : Task()
}

class PipelineParser(private val configFile: File) {
    fun parse(): PipelineConfig {
        // TO DO: implement YAML or JSON parser to read config file
        // For now, return a sample pipeline config
        return PipelineConfig(
            stages = listOf(
                Stage(
                    "build",
                    tasks = listOf(
                        CommandTask("gradle build"),
                        ScriptTask(File("script.sh"))
                    )
                ),
                Stage(
                    "deploy",
                    tasks = listOf(
                        CommandTask("docker push my-image"),
                        ScriptTask(File("deploy.sh"))
                    )
                )
            )
        )
    }
}

fun main() {
    val parser = PipelineParser(File("pipeline-config.yaml"))
    val pipelineConfig = parser.parse()
    println(pipelineConfig)
}