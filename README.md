# int-calculator

Console interactive calculator for integer numbers written in Kotlin.

## Description

This is REPL application that accepts one-line expressions. Addition, subtraction, multiplication, division, and modulo are supported.

Also, user can define variables using let-binding:

`>>> let x = 1`

This variable can be used until evaluation loop is broken.

If given expression has errors, appropriate message will be shown to user without breaking the application:

```
>>> 1 + ?
        ^
Syntax error at position 4: invalid symbol '?'
```

`exit` command terminates REPL:

`>>> exit`

## Build & run

#### Build

`./gradlew installDist`

#### Run

`./build/install/int-calculator/bin/int-calculator`

Or you can use the shortcut

`./calc`

## Example

```
./calc
```

```
>>> 5
5
>>> 1 + -3
-2
>>> -2 * -9
18
>>> 256 / 2 / 2 / 2 / (5 / 2) / 2
8
>>> let x = 15
>>> let y = 13
>>> x*x - y*y
56
>>> let z = 28 - x - y
>>> let z1 = 28 - (x + y + z)
>>> z
0
>>> z1
0
>>> z + z1
0
>>> let z3 = z + z1 + z2
                      ^^
Error: undefined variable: z2

>>> let z3 = z + z1 +
                    ^
Syntax error at position 16: mismatched symbol: '<EOF>'

>>> let z3 = z + z1
>>> z3
0
>>> let z3 = z3 + 1
>>> z3
1
>>> let z3 = z3 + 5738789358493354543354
                  ^^^^^^^^^^^^^^^^^^^^^^
Error: 5738789358493354543354 is not a 32-bit integer

>>> let z3 = z3 + 5738789
>>> z3
5738790
>>> exir
    ^^^^
Error: undefined variable: exir

>>> exit
```