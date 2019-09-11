grammar IntExpr;

line            : letStatement | expression;

letStatement    : LET Identifier ASSIGN expression;

expression      : operator=(PLUS | MINUS) nestedExpression=expression               # unaryExpression
                | LPAREN nestedExpression=expression RPAREN                         # parenthesizedExpression
                | left=expression operator=(MUL | DIV | MOD)     right=expression   # multiplicativeExpression
                | left=expression operator=(PLUS | MINUS)        right=expression   # additiveExpression
                | Identifier                                                        # identifierExpression
                | Literal                                                           # literalExpression;

WHITESPACE      : [\t ]+ -> skip;

LET    : 'let';

LPAREN : '(';
RPAREN : ')';

PLUS   : '+';
MINUS  : '-';
MUL    : '*';
DIV    : '/';
MOD    : '%';
ASSIGN : '=';

Identifier : LetterOrUnderscore (LetterOrUnderscore | Digit)*;

Literal : ZeroDigit | (DigitWithoutZero Digit*) ;

fragment LetterOrUnderscore : [_a-zA-Z] ;

fragment ZeroDigit : [0];

fragment DigitWithoutZero : [1-9];

fragment Digit : [0-9] ;

MismatchedSymbol: . ;