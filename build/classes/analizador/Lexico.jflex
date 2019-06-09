package analizador;

import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%

%cupsym Sym
%class Lexico
%cup
%public
%unicode
%line
%char
%column
%ignorecase


%{
	
	StringBuffer string = new StringBuffer();
	boolean blancos = false;
	
	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}	
  
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
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


<YYINITIAL> "var"			{return symbol(Sym.var_);}
<YYINITIAL> "imprimir"			{return symbol(Sym.imprimir_);}
<YYINITIAL> "importar"			{return symbol(Sym.importar_);}
<YYINITIAL> "detener"			{return symbol(Sym.detener_);}
<YYINITIAL> "retornar"			{return symbol(Sym.retornar_);}
<YYINITIAL> "si"			{return symbol(Sym.si_);}
<YYINITIAL> "sino"			{return symbol(Sym.sino_);}
<YYINITIAL> "selecciona"		{return symbol(Sym.selecciona_);}
<YYINITIAL> "caso"			{return symbol(Sym.caso_);}
<YYINITIAL> "defecto"			{return symbol(Sym.defecto_);}
<YYINITIAL> "funcion"			{return symbol(Sym.funcion_);}

<YYINITIAL> "nulo"			{ return symbol(Sym.nulo_, yytext());}
<YYINITIAL> "verdadero"			{ return symbol(Sym.verdadero_, yytext());}
<YYINITIAL> "falso"			{ return symbol(Sym.falso_, yytext());}


<YYINITIAL>{

\" 					{ string.setLength(0); yybegin(STRING); }
\' 					{ string.setLength(0); yybegin(CHAR); }

"{"					{return symbol(Sym.llaveIzquierda);}
"}"					{return symbol(Sym.llaveDerecha);}
"("					{return symbol(Sym.parIzquierda);}
")"					{return symbol(Sym.parDerecha);}
"["					{return symbol(Sym.corcheteIzquierda);}
"]"					{return symbol(Sym.corcheteDerecha);}
";"					{return symbol(Sym.puntoycoma);}
","					{return symbol(Sym.coma);}
"."					{return symbol(Sym.punto);}
":"					{return symbol(Sym.dospuntos);}
"?"					{return symbol(Sym.interrogacion);}


//Operadores Aritméticos
"+"                 {return symbol(Sym.mas);}
"-"                 {return symbol(Sym.menos);}
"*"                 {return symbol(Sym.asterisco);}  
"/"                 {return symbol(Sym.diagonal);}
"^"                 {return symbol(Sym.potencia);}

"++"                {return symbol(Sym.masmas);}
"--"                {return symbol(Sym.menosmenos);}

//Operadores Relacionales 
">"                 {return symbol(Sym.mayorque);}
"<"                 {return symbol(Sym.menorque);}
">="                {return symbol(Sym.mayorigual);}
"<="                {return symbol(Sym.menorigual);}
"=="                {return symbol(Sym.igualigual);}
"!="                {return symbol(Sym.diferente);}

//Operadores Lógicos
"&&"                {return symbol(Sym.and);}
"||"                {return symbol(Sym.or);}
"!"                 {return symbol(Sym.not);}

//Operador Asignacion
"="                {return symbol(Sym.igual);}
"+="                {return symbol(Sym.masigual);}
"-="                {return symbol(Sym.menosigual);}
"*="                 {return symbol(Sym.porigual);}
"/="                 {return symbol(Sym.diagonaligual);}

{entero}       		{ return symbol(Sym.entero, yytext());}
{decimal}		{ return symbol(Sym.decimal, yytext());}
{identificador}		{ return symbol(Sym.identificador, yytext());}

/* Espacios en Blanco */
{espacioBlanco}			{ /* se ignora */ }

}

<STRING> {
\"                   { yybegin(YYINITIAL);
					   return symbol(Sym.tstring, string.toString()); }
[^\n\r\"\\]+         { string.append( yytext() ); }
\\t                  { string.append('\t'); }
\\n                  { string.append('\n'); }
\\r                  { string.append('\r'); }
\\\"                 { string.append('\"'); }
\\                   { string.append('\\'); }
}

<CHAR> {
\'                   { yybegin(YYINITIAL);
					   return symbol(Sym.tchar, string.toString()); }
[^\n\r\'\\]+         { string.append( yytext() ); }
\\t                  { string.append('\t'); }
\\n                  { string.append('\n'); }
\\r                  { string.append('\r'); }
\\\'                 { string.append('\''); }
\\                   { string.append('\\'); }
}

/* ERRORES LEXICOS */
.		{ System.out.println("Error lexico: "+yytext() + " Linea: "+(yyline+1) + " Columna: "+(yycolumn+1));}











