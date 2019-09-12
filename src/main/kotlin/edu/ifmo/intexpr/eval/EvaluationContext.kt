package edu.ifmo.intexpr.eval

class EvaluationContext<T : Any> private constructor() {
    private val variables = mutableMapOf<String, T>()

    fun save(variableNameAndValue: Pair<String, T>) {
        variables[variableNameAndValue.first] = variableNameAndValue.second
    }

    fun getValue(variableName: String): T? = variables[variableName]

    companion object {
        fun <T : Any> of(vararg variables: Pair<String, T>): EvaluationContext<T> {
            return EvaluationContext<T>().also {
                variables.forEach(it::save)
            }
        }
    }
}