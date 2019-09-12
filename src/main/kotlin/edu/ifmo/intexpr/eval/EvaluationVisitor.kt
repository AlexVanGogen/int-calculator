package edu.ifmo.intexpr.eval

import edu.ifmo.intexpr.error.DivisionByZeroException
import edu.ifmo.intexpr.error.NotAnIntegerException
import edu.ifmo.intexpr.error.UndefinedVariableException
import edu.ifmo.intexpr.parser.IntExprBaseVisitor
import edu.ifmo.intexpr.parser.IntExprParser.*
import kotlin.RuntimeException

class EvaluationVisitor(private val context: EvaluationContext<Int>) : IntExprBaseVisitor<Int?>() {

    override fun visitLetStatement(ctx: LetStatementContext): Int? {
        val variableValue = visitExpression(ctx.expression())
        val variableName = ctx.Identifier().text
        context.save(variableName to variableValue)
        return null
    }

    override fun visitParenthesizedExpression(ctx: ParenthesizedExpressionContext): Int {
        return visitExpression(ctx.nestedExpression)
    }

    override fun visitMultiplicativeExpression(ctx: MultiplicativeExpressionContext): Int {
        val rightOperandValue = visitExpression(ctx.right)
        if (ctx.operator.type in arrayOf(DIV, MOD)) {
            if (rightOperandValue == 0)
                throw DivisionByZeroException(ctx.right.start.startIndex, ctx.right.stop.stopIndex)
        }
        val leftOperandValue = visitExpression(ctx.left)
        return when (ctx.operator.type) {
            MUL -> leftOperandValue * rightOperandValue
            DIV -> leftOperandValue / rightOperandValue
            MOD -> leftOperandValue % rightOperandValue
            else -> throw RuntimeException()
        }
    }

    override fun visitAdditiveExpression(ctx: AdditiveExpressionContext): Int {
        val leftOperandValue = visitExpression(ctx.left)
        val rightOperandValue = visitExpression(ctx.right)
        return when (ctx.operator.type) {
            PLUS -> leftOperandValue + rightOperandValue
            MINUS -> leftOperandValue - rightOperandValue
            else -> throw RuntimeException()
        }
    }

    override fun visitIdentifierExpression(ctx: IdentifierExpressionContext): Int {
        val variable = ctx.Identifier()
        val variableName = variable.text
        return context.getValue(variableName) ?: throw UndefinedVariableException(
            variableName,
            variable.symbol.startIndex,
            variable.symbol.stopIndex
        )
    }

    override fun visitLiteralExpression(ctx: LiteralExpressionContext): Int {
        return ctx.text.toIntOrNull() ?: throw NotAnIntegerException(
            ctx.text,
            ctx.start.startIndex,
            ctx.stop.stopIndex
        )
    }

    override fun visitUnaryExpression(ctx: UnaryExpressionContext): Int {
        if (ctx.operator.type == MINUS
            && ctx.nestedExpression is LiteralExpressionContext
            && "-" + ctx.nestedExpression.text == Int.MIN_VALUE.toString()) {
            return Int.MIN_VALUE
        }
        val operandValue = visitExpression(ctx.nestedExpression)
        return when (ctx.operator.type) {
            PLUS -> operandValue
            MINUS -> -operandValue
            else -> throw RuntimeException()
        }
    }

    private fun visitExpression(ctx: ExpressionContext): Int = when (ctx) {
        is ParenthesizedExpressionContext -> visitParenthesizedExpression(ctx)
        is MultiplicativeExpressionContext -> visitMultiplicativeExpression(ctx)
        is AdditiveExpressionContext -> visitAdditiveExpression(ctx)
        is IdentifierExpressionContext -> visitIdentifierExpression(ctx)
        is LiteralExpressionContext -> visitLiteralExpression(ctx)
        is UnaryExpressionContext -> visitUnaryExpression(ctx)
        else -> throw RuntimeException()
    }
}