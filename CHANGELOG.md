# [02/19/24]
## Added
- `Stmt` class and wired up `Interpreter` to execute statement objects
- `RuntimeError` for errors occured during `Interpreter.interpret()`
- Test code: `test.lx` for testing transpilation process.

## Updated
- Grammar rules:
    - new:
        - `program` -> `declaration` `EOF` ;
        - `declaration` -> `varDecl` | `statement` ;
        - `varDecl` -> "var" `IDENTIFIER` ( "=" `expression` )? ";";
    - updated:
        `primary` new option: `IDENTIFIER`
- `Parser.parse()` now returns a list of `Stmt` objects instead of expressions.

# [02/17/24]
## Added
- Create Parser (Recursive Descent Parser) with Grammar rules for parsing seqeuence of Tokens into expressions.
    - Grammar rules (Highest precendence at the top)
        - `expression` -> `equality`;
        - `equality` -> `comparison` ( ( "!=" | "++" ) `comparision` )* ;
        - `comparision` -> `term` ( ( "<" | "<=" | ">" | ">=" ) `term` )* ;
        - `term` -> `factor` ( ( "-" | "+" ) `factor` )* ;
        - `factor` -> `unary` ( ( "*" | "/" ) `unary` )* ;
        - `unary` -> ( "!" | "-" ) `unary` | `primary` ;
        - `primary` -> `NUMBER` | `STRING` | "true" | "false" | "nil" | "(" `expression` ")" ;
- Basic Syntax Errors for when parsing finds an issue generating expressions from Token sequence
- `Parser.synchronize()` to get parsing back on track after encountering a syntax error.
- `Interpreter.java` for converting `Expressions` into Java code.

# [02/14/24]
## Added
- Created Expr classes
    - Implemented Visitor pattern for Expr subclasses
        - Visitor Interface for handling visitor objects to each Expr subclass
            - `Visitor.visitBinaryExpr()`
            - `Visitor.visitGroupingExpr()`
            - `Visitor.visitLiteralExpr()`
            - `Visitor.visitUnaryExpr()`
        - Added `Expr.accept()` override function to each Expr subclass to call their specific visitor function
        - This pattern approximates a functional style programming within OOP, making it easier to create new functions for the Expr subclasses. Conversely, it makes it more difficult to add a new Expr subclass.
            - For more explanation, see `5.3.2 The Visitor Pattern`
    - Current class structure:
        - Grouping class
            - Expression
        - Literal class
            - Value
        - Unary class
            - Operator Token
            - Right Expression
        - Binary class
            - Left Expression
            - Operator Token
            - Right Expression
    - Generated using `com.rdstew.tool.GenerateAST` for quickly generating the `Expr.java` file with defined subclasses.
        

- Created AstPrinter class for printing out ASTs in a more readable form
    - Utilizes Visitor pattern to recursively generate strings out of nested Expr objects.


# [02/10/24] 
## C/C++ Setup
- Installed MSYS2 to `\opt\`
- MSYS2 command to instal mingw lib: `pacman -S --needed base-devel mingw-w64-ucrt-x86_64-toolchain`
- Added `\MSYS2\ucrt64\bin` to PATH

## Java Lox
- Completed Scanner implementation
    - Added minimum required token types
    - Multiline comments are allowed
    - Multicharacter parsing for !, =, /, <, >
    - Identifier parsing
    - Added block comment parsing
    - Alphanumeric Character check based on char value
    
