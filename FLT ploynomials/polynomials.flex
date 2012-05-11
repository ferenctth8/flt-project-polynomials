package flt.polynomialInterpreter.parser;
import java_cup.runtime.*;
%%

%public 
%class PolynomialInterpreterLexer

%line
%column
%cup

%{   
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%%

"X" { return symbol(sym.TERMX); }
"^^" { return symbol(sym.POWER_OP); }
"+"  { return symbol(sym.PLUS); }
"-"  { return symbol(sym.MINUS); }
"*"  { return symbol(sym.TIMES); }
"/"  { return symbol(sym.DIVIDE); }
";"  { return symbol(sym.SEMI); } 
"Integrate" { return symbol(sym.INTEGRAL); }
"Differentiate" { return symbol(sym.DIFFERENTIAL); }
")"  { return symbol(sym.RBRACKET); }
"("  { return symbol(sym.LBRACKET); }
"="  { return symbol(sym.EQUAL); }
[0-9]+ { return symbol(sym.NUMBER); }
[A-Z]{1} { return symbol(sym.POL_VAR); }
[a-z]+ { return symbol(sym.VAR); }
.|\n { }

