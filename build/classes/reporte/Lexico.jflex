package reporte;

import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%

%cupsym ReporteSym
%class ReporteLexico
%cup
%public
%unicode
%line
%char
%column


%{
	
	StringBuffer string = new StringBuffer();
	boolean blancos = false;

        
	private Symbol symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn+1);
	}	
  
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn+1, value);
	}
%}

digito = [0-9]
entero = {digito}+
decimal = {digito}+"."{digito}+
letra = [a-zA-ZñÑ]
identificador = ({letra}|"_")({letra}|{digito}|"_")*

finLinea = \r|\n|\r\n
espacioBlanco = {finLinea} | [ \t\f]

COMENT_SIMPLE ="//" [^\r\n]* {finLinea}?
COMENT_MULTI ="/*""/"*([^*/]|[^*]"/"|"*"[^/])*"*"*"*/"

%state STRING
%state CHAR

%%

{COMENT_SIMPLE} 	{/* se ignora */} 
{COMENT_MULTI} 		{/* se ignora */} 

/* Palabras Reservadas*/
<YYINITIAL> "public"			{ return symbol(ReporteSym.public_);}
<YYINITIAL> "protected"			{ return symbol(ReporteSym.protected_);}
<YYINITIAL> "private"			{ return symbol(ReporteSym.private_);}
<YYINITIAL> "abstract"			{ return symbol(ReporteSym.abstract_);}
<YYINITIAL> "static"			{ return symbol(ReporteSym.static_);}
<YYINITIAL> "final"			{ return symbol(ReporteSym.final_);}
<YYINITIAL> "extends"			{ return symbol(ReporteSym.extends_);}
<YYINITIAL> "class"			{ return symbol(ReporteSym.class_);}
<YYINITIAL> "int"			{ return symbol(ReporteSym.int_);}
<YYINITIAL> "double"			{ return symbol(ReporteSym.double_);}
<YYINITIAL> "char"			{ return symbol(ReporteSym.char_);}
<YYINITIAL> "boolean"			{ return symbol(ReporteSym.boolean_);}
<YYINITIAL> "void"			{ return symbol(ReporteSym.void_);}

<YYINITIAL> "null"			{ return symbol(ReporteSym.null_);}
<YYINITIAL> "true"			{ return symbol(ReporteSym.true_);}
<YYINITIAL> "false"			{ return symbol(ReporteSym.false_);}

<YYINITIAL> "print"			{ return symbol(ReporteSym.print_);}
<YYINITIAL> "println"			{ return symbol(ReporteSym.println_);}
<YYINITIAL> "printTabla"                { return symbol(ReporteSym.printTabla_);}
<YYINITIAL> "return"			{ return symbol(ReporteSym.return_);}

<YYINITIAL> "if"			{ return symbol(ReporteSym.if_);}
<YYINITIAL> "else"			{ return symbol(ReporteSym.else_);}
<YYINITIAL> "break"                     { return symbol(ReporteSym.break_);}
<YYINITIAL> "continue"                  { return symbol(ReporteSym.continue_);}
<YYINITIAL> "while"                     { return symbol(ReporteSym.while_);}
<YYINITIAL> "do"                        { return symbol(ReporteSym.do_);}
<YYINITIAL> "for"                       { return symbol(ReporteSym.for_);}
<YYINITIAL> "switch"                    { return symbol(ReporteSym.switch_);}
<YYINITIAL> "case"                      { return symbol(ReporteSym.case_);}
<YYINITIAL> "default"                   { return symbol(ReporteSym.default_);}
<YYINITIAL> "import"                    { return symbol(ReporteSym.import_);}
<YYINITIAL> "new"                       { return symbol(ReporteSym.new_);}
<YYINITIAL> "read_file"                 { return symbol(ReporteSym.read_file_);}
<YYINITIAL> "write_file"                { return symbol(ReporteSym.write_file_);}
<YYINITIAL> "graph"                     { return symbol(ReporteSym.graph_);}
<YYINITIAL> "this"                      { return symbol(ReporteSym.this_);}
<YYINITIAL> "super"                     { return symbol(ReporteSym.super_);}
<YYINITIAL> "instanceof"                { return symbol(ReporteSym.instanceof_);}

<YYINITIAL>{

\" 					{ string.setLength(0); yybegin(STRING); }
\' 					{ string.setLength(0); yybegin(CHAR); }

"{"					{return symbol(ReporteSym.llaveIzquierda);}
"}"					{return symbol(ReporteSym.llaveDerecha);}
"("					{return symbol(ReporteSym.parIzquierda);}
")"					{return symbol(ReporteSym.parDerecha);}
"["					{return symbol(ReporteSym.corcheteIzquierda);}
"]"					{return symbol(ReporteSym.corcheteDerecha);}
";"					{return symbol(ReporteSym.puntoycoma);}
","					{return symbol(ReporteSym.coma);}
"."					{return symbol(ReporteSym.punto);}
":"					{return symbol(ReporteSym.dospuntos);}
"?"					{return symbol(ReporteSym.interrogacion);}


//Operadores Aritméticos
"+"                 {return symbol(ReporteSym.mas);}
"-"                 {return symbol(ReporteSym.menos);}
"*"                 {return symbol(ReporteSym.asterisco);}  
"/"                 {return symbol(ReporteSym.diagonal);}
"%"                 {return symbol(ReporteSym.modulo);}

"++"                {return symbol(ReporteSym.masmas);}
"--"                {return symbol(ReporteSym.menosmenos);}

//Operadores Relacionales 
">"                 {return symbol(ReporteSym.mayorque);}
"<"                 {return symbol(ReporteSym.menorque);}
">="                {return symbol(ReporteSym.mayorigual);}
"<="                {return symbol(ReporteSym.menorigual);}
"=="                {return symbol(ReporteSym.igualigual);}
"!="                {return symbol(ReporteSym.diferente);}

//Operadores Lógicos
"&&"                {return symbol(ReporteSym.and);}
"||"                {return symbol(ReporteSym.or);}
"!"                 {return symbol(ReporteSym.not);}
"^"                 {return symbol(ReporteSym.xor);}

//Operador Asignacion
"="                 {return symbol(ReporteSym.igual);}

{entero}       		{ return symbol(ReporteSym.entero, yytext());}
{decimal}		{ return symbol(ReporteSym.decimal, yytext());}
{identificador}         { return symbol(ReporteSym.id, yytext());}

/* Espacios en Blanco */
{espacioBlanco}         { /*Se ignoran*/ }

}

<STRING> {
\"                   { yybegin(YYINITIAL);
					   return symbol(ReporteSym.tstring, string.toString()); }
[^\n\r\"\\]+         { string.append( yytext() ); }
\\t                  { string.append('\t'); }
\\n                  { string.append('\n'); }
\\r                  { string.append('\r'); }
\\\"                 { string.append('\"'); }
\\                   { string.append('\\'); }
}

<CHAR> {
\'                   { yybegin(YYINITIAL);
					   return symbol(ReporteSym.tchar, string.toString()); }
[^\n\r\'\\]+         { string.append( yytext() ); }
\\t                  { string.append('\t'); }
\\n                  { string.append('\n'); }
\\r                  { string.append('\r'); }
\\\'                 { string.append('\''); }
\\                   { string.append('\\'); }
}

/* ERRORES LEXICOS */
.		{ System.out.println("Error lexico: "+yytext() + " Linea: "+(yyline+1) + " Columna: "+(yycolumn+1));}

                











