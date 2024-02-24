package com.rdstew.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;

    LoxFunction(Stmt.Function decalaration) {
        this.declaration = decalaration;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        // Create function scope and execute body
        Environment env = new Environment();
        for (int i = 0; i < declaration.params.size(); i++) {
            env.define(declaration.params.get(i).lexeme, arguments.get(i));
        }

        interpreter.executeBlock(declaration.body, env);
        return null;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}