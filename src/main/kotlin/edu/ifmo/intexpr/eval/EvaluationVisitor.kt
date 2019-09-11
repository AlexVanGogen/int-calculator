package edu.ifmo.intexpr.eval

import edu.ifmo.intexpr.parser.IntExprBaseVisitor
import edu.ifmo.intexpr.parser.IntExprParser.*
import kotlin.RuntimeException

class EvaluationVisitor : IntExprBaseVisitor<Int>() {

    override fun visitParenthesizedExpression(ctx: ParenthesizedExpressionContext): Int {
        return visitExpression(ctx.nestedExpression)
    }

    override fun visitMultiplicativeExpression(ctx: MultiplicativeExpressionContext): Int {
        val leftOperandValue = visitExpression(ctx.left)
        val rightOperandValue = visitExpression(ctx.right)
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

    override fun visitLiteralExpression(ctx: LiteralExpressionContext): Int {
        return ctx.text.toInt()
    }

    override fun visitUnaryExpression(ctx: UnaryExpressionContext): Int {
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