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
        case.*/
     private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
     }
     
     /* Also we wil create a java_cup.runtime.Symbol with information
        about the current token, but this object will have a value.*/
     private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
     }   
}%

%%
[A-Z]{1} { return symbol(sym.POL_VAR);  }
"X" { return symbol(sym.TERMX); }
"^^"[0-9]+ { return symbol(sym.POWER_OP); }
"+"  { return symbol(sym.PLUS); }
"-"  { return symbol(sym.MINUS); }
"*"  { return symbol(sym.TIMES); }
"/"  { return symbol(sym.DIVIDE); }
")"  { return symbol(sym.RBRACKET); }
"("  { return symbol(sym.LBRACKET); }
"I" { return symbol(sym.INTEGRAL); }
"D" { return symbol(sym.DIFFERENTIAL); }
[0-9]+ { return symbol(sym.NUMBER); }
";"  { return symbol(sym.SEMI); } 
.|\n { }
