/* The following code was generated by JFlex 1.6.1 */

package reporte;

import java_cup.runtime.Symbol;
import java.util.ArrayList;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>lexico.jflex</tt>
 */
public class ReporteLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;
  public static final int CHAR = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\7\1\6\1\70\1\7\1\5\22\0\1\7\1\63\1\41"+
    "\2\0\1\57\1\64\1\42\1\45\1\46\1\11\1\55\1\52\1\56"+
    "\1\2\1\10\12\1\1\53\1\51\1\61\1\62\1\60\1\54\1\0"+
    "\23\3\1\34\6\3\1\47\1\67\1\50\1\66\1\4\1\0\1\26"+
    "\1\14\1\17\1\24\1\23\1\30\1\40\1\33\1\16\1\3\1\35"+
    "\1\15\1\37\1\31\1\21\1\12\1\3\1\20\1\27\1\22\1\13"+
    "\1\25\1\36\1\32\2\3\1\43\1\65\1\44\7\0\1\70\53\0"+
    "\1\3\21\0\1\3\u1f54\0\1\3\17\0\1\70\1\70\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\4\2\5\1\6\1\7"+
    "\17\4\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\2\1\1\33\2\34\1\35"+
    "\1\36\2\34\1\37\1\36\1\0\1\40\1\0\5\4"+
    "\1\41\13\4\1\42\16\4\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\1\51\1\52\1\34\1\53\1\54\1\55"+
    "\1\56\1\34\1\57\1\60\2\40\1\0\3\4\1\61"+
    "\2\4\1\62\24\4\1\63\2\4\1\64\3\4\2\34"+
    "\2\0\12\4\1\65\1\66\2\4\1\67\1\70\1\71"+
    "\3\4\1\72\6\4\1\73\3\4\1\34\1\40\2\34"+
    "\1\40\1\34\1\0\1\40\2\4\1\74\1\4\1\75"+
    "\3\4\1\76\7\4\1\77\2\4\1\100\1\101\1\4"+
    "\1\102\1\103\1\34\1\40\1\34\1\40\1\104\6\4"+
    "\1\105\1\4\1\106\2\4\1\107\2\4\1\110\1\111"+
    "\1\4\1\112\1\113\2\4\1\114\3\4\1\115\1\116"+
    "\5\4\1\117\1\4\1\120\2\4\1\121\1\4\1\122"+
    "\1\4\1\123\1\124\1\125";

  private static int [] zzUnpackAction() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\71\0\162\0\253\0\344\0\253\0\u011d\0\u0156"+
    "\0\253\0\u018f\0\253\0\u01c8\0\u0201\0\u023a\0\u0273\0\u02ac"+
    "\0\u02e5\0\u031e\0\u0357\0\u0390\0\u03c9\0\u0402\0\u043b\0\u0474"+
    "\0\u04ad\0\u04e6\0\253\0\253\0\253\0\253\0\253\0\253"+
    "\0\253\0\253\0\253\0\253\0\253\0\253\0\u051f\0\u0558"+
    "\0\253\0\u0591\0\u05ca\0\u0603\0\u063c\0\u0675\0\u06ae\0\253"+
    "\0\u06e7\0\u0720\0\253\0\u0759\0\u0792\0\u07cb\0\253\0\u0804"+
    "\0\u083d\0\u0876\0\u08af\0\u08e8\0\u0921\0\u095a\0\u0993\0\u09cc"+
    "\0\u011d\0\u0a05\0\u0a3e\0\u0a77\0\u0ab0\0\u0ae9\0\u0b22\0\u0b5b"+
    "\0\u0b94\0\u0bcd\0\u0c06\0\u0c3f\0\u0c78\0\u0cb1\0\u0cea\0\u0d23"+
    "\0\u0d5c\0\u0d95\0\u0dce\0\u0e07\0\u0e40\0\u0e79\0\u0eb2\0\u0eeb"+
    "\0\u0f24\0\u0f5d\0\u0f96\0\253\0\253\0\253\0\253\0\253"+
    "\0\253\0\253\0\253\0\u0fcf\0\253\0\253\0\253\0\253"+
    "\0\u1008\0\253\0\u083d\0\u1041\0\253\0\u107a\0\u10b3\0\u10ec"+
    "\0\u1125\0\u011d\0\u115e\0\u1197\0\u011d\0\u11d0\0\u1209\0\u1242"+
    "\0\u127b\0\u12b4\0\u12ed\0\u1326\0\u135f\0\u1398\0\u13d1\0\u140a"+
    "\0\u1443\0\u147c\0\u14b5\0\u14ee\0\u1527\0\u1560\0\u1599\0\u15d2"+
    "\0\u160b\0\u011d\0\u1644\0\u167d\0\u011d\0\u16b6\0\u16ef\0\u1728"+
    "\0\u1761\0\u179a\0\u17d3\0\u180c\0\u1845\0\u187e\0\u18b7\0\u18f0"+
    "\0\u1929\0\u1962\0\u199b\0\u19d4\0\u1a0d\0\u1a46\0\u011d\0\u011d"+
    "\0\u1a7f\0\u1ab8\0\u011d\0\u011d\0\u011d\0\u1af1\0\u1b2a\0\u1b63"+
    "\0\u011d\0\u1b9c\0\u1bd5\0\u1c0e\0\u1c47\0\u1c80\0\u1cb9\0\u011d"+
    "\0\u1cf2\0\u1d2b\0\u1d64\0\u1d9d\0\u06e7\0\u1dd6\0\u1e0f\0\u0792"+
    "\0\u1e48\0\u1e81\0\u1e81\0\u1eba\0\u1ef3\0\u1f2c\0\u1f65\0\u011d"+
    "\0\u1f9e\0\u1fd7\0\u2010\0\u011d\0\u2049\0\u2082\0\u20bb\0\u20f4"+
    "\0\u212d\0\u2166\0\u219f\0\u011d\0\u21d8\0\u2211\0\u011d\0\u011d"+
    "\0\u224a\0\u011d\0\u011d\0\u2283\0\u2283\0\u22bc\0\u22bc\0\u011d"+
    "\0\u22f5\0\u232e\0\u2367\0\u23a0\0\u23d9\0\u2412\0\u011d\0\u244b"+
    "\0\u011d\0\u2484\0\u24bd\0\u011d\0\u24f6\0\u252f\0\u011d\0\u011d"+
    "\0\u2568\0\u011d\0\u011d\0\u25a1\0\u25da\0\u011d\0\u2613\0\u264c"+
    "\0\u2685\0\u011d\0\u011d\0\u26be\0\u26f7\0\u2730\0\u2769\0\u27a2"+
    "\0\u011d\0\u27db\0\u011d\0\u2814\0\u284d\0\u011d\0\u2886\0\u011d"+
    "\0\u28bf\0\u011d\0\u011d\0\u011d";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\2\7\1\10\2\11\1\12\1\13"+
    "\1\14\1\7\1\15\1\7\1\16\1\17\1\20\1\7"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\4\7\1\31\1\7\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\1\51\1\52\1\53\1\54\1\55\1\56"+
    "\1\57\1\60\1\4\1\0\5\61\2\0\1\61\1\62"+
    "\30\61\1\63\25\61\1\64\1\61\5\65\2\0\1\65"+
    "\1\66\31\65\1\67\24\65\1\70\1\65\72\0\1\5"+
    "\1\71\67\0\1\7\1\0\2\7\5\0\27\7\36\0"+
    "\1\11\72\0\1\72\1\73\60\0\1\7\1\0\2\7"+
    "\5\0\1\7\1\74\4\7\1\75\1\76\17\7\31\0"+
    "\1\7\1\0\2\7\5\0\6\7\1\77\1\100\17\7"+
    "\31\0\1\7\1\0\2\7\5\0\16\7\1\101\1\102"+
    "\5\7\1\103\1\7\31\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\104\3\7\1\105\4\7\1\106\4\7\1\107"+
    "\5\7\31\0\1\7\1\0\2\7\5\0\11\7\1\110"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\6\7\1\111"+
    "\12\7\1\112\5\7\31\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\113\14\7\1\114\6\7\31\0\1\7\1\0"+
    "\2\7\5\0\7\7\1\115\1\7\1\116\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\7\7\1\117\17\7\31\0"+
    "\1\7\1\0\2\7\5\0\2\7\1\120\24\7\31\0"+
    "\1\7\1\0\2\7\5\0\1\7\1\121\6\7\1\122"+
    "\13\7\1\123\2\7\31\0\1\7\1\0\2\7\5\0"+
    "\4\7\1\124\2\7\1\125\4\7\1\126\12\7\31\0"+
    "\1\7\1\0\2\7\5\0\1\7\1\127\7\7\1\130"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\6\7\1\131"+
    "\12\7\1\132\5\7\31\0\1\7\1\0\2\7\5\0"+
    "\6\7\1\133\20\7\105\0\1\134\71\0\1\135\74\0"+
    "\1\136\70\0\1\137\70\0\1\140\70\0\1\141\72\0"+
    "\1\142\71\0\1\143\3\0\5\61\2\0\32\61\1\0"+
    "\25\61\1\0\6\61\2\0\1\61\1\72\1\144\27\61"+
    "\1\0\25\61\1\0\1\61\20\0\1\145\1\0\1\146"+
    "\6\0\1\147\7\0\1\150\27\0\5\65\2\0\33\65"+
    "\1\0\24\65\1\0\6\65\2\0\1\65\1\72\1\151"+
    "\30\65\1\0\24\65\1\0\1\65\20\0\1\145\1\0"+
    "\1\146\6\0\1\147\10\0\1\152\27\0\1\153\67\0"+
    "\5\72\1\154\1\155\62\72\11\73\1\156\57\73\1\0"+
    "\1\7\1\0\2\7\5\0\2\7\1\157\24\7\31\0"+
    "\1\7\1\0\2\7\5\0\4\7\1\160\2\7\1\161"+
    "\17\7\31\0\1\7\1\0\2\7\5\0\24\7\1\162"+
    "\2\7\31\0\1\7\1\0\2\7\5\0\11\7\1\163"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\7\7\1\164"+
    "\17\7\31\0\1\7\1\0\2\7\5\0\10\7\1\165"+
    "\4\7\1\166\11\7\31\0\1\7\1\0\2\7\5\0"+
    "\1\167\26\7\31\0\1\7\1\0\2\7\5\0\14\7"+
    "\1\170\12\7\31\0\1\7\1\0\2\7\5\0\17\7"+
    "\1\171\7\7\31\0\1\7\1\0\2\7\5\0\15\7"+
    "\1\172\11\7\31\0\1\7\1\0\2\7\5\0\14\7"+
    "\1\173\12\7\31\0\1\7\1\0\2\7\5\0\10\7"+
    "\1\174\3\7\1\175\12\7\31\0\1\7\1\0\2\7"+
    "\5\0\1\7\1\176\25\7\31\0\1\7\1\0\2\7"+
    "\5\0\4\7\1\177\22\7\31\0\1\7\1\0\2\7"+
    "\5\0\15\7\1\200\11\7\31\0\1\7\1\0\2\7"+
    "\5\0\10\7\1\201\16\7\31\0\1\7\1\0\2\7"+
    "\5\0\1\7\1\202\25\7\31\0\1\7\1\0\2\7"+
    "\5\0\16\7\1\203\10\7\31\0\1\7\1\0\2\7"+
    "\5\0\4\7\1\204\22\7\31\0\1\7\1\0\2\7"+
    "\5\0\15\7\1\205\11\7\31\0\1\7\1\0\2\7"+
    "\5\0\1\206\26\7\31\0\1\7\1\0\2\7\5\0"+
    "\14\7\1\207\12\7\31\0\1\7\1\0\2\7\5\0"+
    "\4\7\1\210\22\7\31\0\1\7\1\0\2\7\5\0"+
    "\17\7\1\211\7\7\31\0\1\7\1\0\2\7\5\0"+
    "\6\7\1\212\20\7\31\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\213\23\7\31\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\214\23\7\31\0\1\7\1\0\2\7\5\0"+
    "\24\7\1\215\2\7\31\0\1\7\1\0\2\7\5\0"+
    "\4\7\1\216\22\7\31\0\1\7\1\0\2\7\5\0"+
    "\4\7\1\217\22\7\31\0\1\7\1\0\2\7\5\0"+
    "\14\7\1\220\12\7\30\0\5\144\2\73\2\144\1\221"+
    "\27\144\1\73\25\144\1\73\1\144\5\151\2\73\2\151"+
    "\1\222\30\151\1\73\24\151\1\73\1\151\6\0\1\155"+
    "\62\0\10\223\1\155\1\224\57\223\1\0\1\7\1\0"+
    "\2\7\5\0\3\7\1\225\23\7\31\0\1\7\1\0"+
    "\2\7\5\0\13\7\1\226\3\7\1\227\7\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\230\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\14\7\1\231\12\7\31\0"+
    "\1\7\1\0\2\7\5\0\3\7\1\232\23\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\233\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\7\7\1\234\17\7\31\0"+
    "\1\7\1\0\2\7\5\0\15\7\1\235\11\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\236\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\11\7\1\237\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\6\7\1\240\20\7\31\0"+
    "\1\7\1\0\2\7\5\0\1\7\1\241\25\7\31\0"+
    "\1\7\1\0\2\7\5\0\12\7\1\242\14\7\31\0"+
    "\1\7\1\0\2\7\5\0\11\7\1\243\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\15\7\1\244\11\7\31\0"+
    "\1\7\1\0\2\7\5\0\11\7\1\245\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\11\7\1\246\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\2\7\1\247\24\7\31\0"+
    "\1\7\1\0\2\7\5\0\14\7\1\250\12\7\31\0"+
    "\1\7\1\0\2\7\5\0\12\7\1\251\14\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\252\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\11\7\1\253\15\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\254\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\255\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\14\7\1\256\12\7\31\0"+
    "\1\7\1\0\2\7\5\0\15\7\1\257\11\7\31\0"+
    "\1\7\1\0\2\7\5\0\3\7\1\260\23\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\261\16\7\31\0"+
    "\1\7\1\0\2\7\5\0\3\7\1\262\23\7\31\0"+
    "\1\7\1\0\2\7\5\0\1\263\26\7\30\0\5\264"+
    "\2\223\1\264\1\265\1\266\27\264\1\223\25\264\1\223"+
    "\1\264\5\267\2\223\1\267\1\270\1\271\30\267\1\223"+
    "\24\267\1\223\1\267\10\73\1\272\1\156\67\73\1\273"+
    "\1\156\57\73\1\0\1\7\1\0\2\7\5\0\4\7"+
    "\1\274\22\7\31\0\1\7\1\0\2\7\5\0\14\7"+
    "\1\275\12\7\31\0\1\7\1\0\2\7\5\0\10\7"+
    "\1\276\16\7\31\0\1\7\1\0\2\7\5\0\11\7"+
    "\1\277\15\7\31\0\1\7\1\0\2\7\5\0\23\7"+
    "\1\300\3\7\31\0\1\7\1\0\2\7\5\0\11\7"+
    "\1\301\15\7\31\0\1\7\1\0\2\7\5\0\14\7"+
    "\1\302\12\7\31\0\1\7\1\0\2\7\5\0\6\7"+
    "\1\303\20\7\31\0\1\7\1\0\2\7\5\0\15\7"+
    "\1\304\11\7\31\0\1\7\1\0\2\7\5\0\4\7"+
    "\1\305\22\7\31\0\1\7\1\0\2\7\5\0\6\7"+
    "\1\306\20\7\31\0\1\7\1\0\1\7\1\307\5\0"+
    "\27\7\31\0\1\7\1\0\2\7\5\0\17\7\1\310"+
    "\7\7\31\0\1\7\1\0\2\7\5\0\3\7\1\311"+
    "\23\7\31\0\1\7\1\0\2\7\5\0\1\7\1\312"+
    "\25\7\31\0\1\7\1\0\2\7\5\0\6\7\1\313"+
    "\20\7\31\0\1\7\1\0\2\7\5\0\6\7\1\314"+
    "\20\7\31\0\1\7\1\0\2\7\5\0\4\7\1\315"+
    "\22\7\31\0\1\7\1\0\2\7\5\0\5\7\1\316"+
    "\21\7\31\0\1\7\1\0\2\7\5\0\3\7\1\317"+
    "\23\7\31\0\1\7\1\0\2\7\5\0\11\7\1\320"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\11\7\1\321"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\11\7\1\322"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\21\7\1\323"+
    "\5\7\30\0\5\144\2\73\1\144\1\324\1\221\27\144"+
    "\1\73\25\144\1\73\6\144\2\73\1\144\1\325\1\221"+
    "\27\144\1\73\25\144\1\73\1\144\5\151\2\73\1\151"+
    "\1\326\1\222\30\151\1\73\24\151\1\73\6\151\2\73"+
    "\1\151\1\327\1\222\30\151\1\73\24\151\1\73\1\151"+
    "\10\0\1\223\61\0\1\7\1\0\2\7\5\0\5\7"+
    "\1\330\21\7\31\0\1\7\1\0\2\7\5\0\10\7"+
    "\1\331\16\7\31\0\1\7\1\0\2\7\5\0\3\7"+
    "\1\332\16\7\1\333\4\7\31\0\1\7\1\0\2\7"+
    "\5\0\5\7\1\334\21\7\31\0\1\7\1\0\2\7"+
    "\5\0\14\7\1\335\12\7\31\0\1\7\1\0\2\7"+
    "\5\0\17\7\1\336\7\7\31\0\1\7\1\0\2\7"+
    "\5\0\10\7\1\337\16\7\31\0\1\7\1\0\2\7"+
    "\5\0\17\7\1\340\7\7\31\0\1\7\1\0\2\7"+
    "\5\0\17\7\1\341\7\7\31\0\1\7\1\0\2\7"+
    "\5\0\16\7\1\342\10\7\31\0\1\7\1\0\2\7"+
    "\5\0\12\7\1\343\14\7\31\0\1\7\1\0\2\7"+
    "\5\0\11\7\1\344\15\7\31\0\1\7\1\0\2\7"+
    "\5\0\3\7\1\345\23\7\31\0\1\7\1\0\2\7"+
    "\5\0\14\7\1\346\12\7\31\0\1\7\1\0\2\7"+
    "\5\0\5\7\1\347\21\7\31\0\1\7\1\0\2\7"+
    "\5\0\21\7\1\350\5\7\31\0\1\7\1\0\1\7"+
    "\1\351\5\0\27\7\30\0\5\61\2\0\1\61\1\264"+
    "\30\61\1\0\25\61\1\0\1\61\5\65\2\0\1\65"+
    "\1\267\31\65\1\0\24\65\1\0\1\65\1\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\352\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\17\7\1\353\7\7\31\0\1\7"+
    "\1\0\2\7\5\0\14\7\1\354\12\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\355\16\7\31\0\1\7"+
    "\1\0\2\7\5\0\17\7\1\356\7\7\31\0\1\7"+
    "\1\0\2\7\5\0\5\7\1\357\21\7\31\0\1\7"+
    "\1\0\2\7\5\0\1\7\1\360\25\7\31\0\1\7"+
    "\1\0\2\7\5\0\4\7\1\361\22\7\31\0\1\7"+
    "\1\0\2\7\5\0\15\7\1\362\11\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\363\16\7\31\0\1\7"+
    "\1\0\2\7\5\0\5\7\1\364\21\7\31\0\1\7"+
    "\1\0\2\7\5\0\16\7\1\365\10\7\31\0\1\7"+
    "\1\0\2\7\5\0\2\7\1\366\24\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\367\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\370\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\371\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\3\7\1\372\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\373\16\7\31\0\1\7"+
    "\1\0\2\7\5\0\4\7\1\374\22\7\31\0\1\7"+
    "\1\0\2\7\5\0\3\7\1\375\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\12\7\1\376\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\7\7\1\377\17\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\u0100\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\3\7\1\u0101\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\14\7\1\u0102\12\7\31\0\1\7"+
    "\1\0\2\7\5\0\16\7\1\u0103\10\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\u0104\15\7\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10488];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\1\1\1\11\2\1\1\11\1\1\1\11"+
    "\17\1\14\11\2\1\1\11\6\1\1\11\2\1\1\11"+
    "\3\1\1\11\1\1\1\0\1\1\1\0\40\1\10\11"+
    "\1\1\4\11\1\1\1\11\2\1\1\11\1\0\44\1"+
    "\2\0\45\1\1\0\112\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[260];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
	
	StringBuffer string = new StringBuffer();
	boolean blancos = false;

        
	private Symbol symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn+1);
	}	
  
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn+1, value);
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ReporteLexico(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 190) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(ReporteSym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.out.println("Error lexico: "+yytext() + " Linea: "+(yyline+1) + " Columna: "+(yycolumn+1));
            }
          case 86: break;
          case 2: 
            { return symbol(ReporteSym.entero, yytext());
            }
          case 87: break;
          case 3: 
            { return symbol(ReporteSym.punto);
            }
          case 88: break;
          case 4: 
            { return symbol(ReporteSym.id, yytext());
            }
          case 89: break;
          case 5: 
            { /*Se ignoran*/
            }
          case 90: break;
          case 6: 
            { return symbol(ReporteSym.diagonal);
            }
          case 91: break;
          case 7: 
            { return symbol(ReporteSym.asterisco);
            }
          case 92: break;
          case 8: 
            { string.setLength(0); yybegin(STRING);
            }
          case 93: break;
          case 9: 
            { string.setLength(0); yybegin(CHAR);
            }
          case 94: break;
          case 10: 
            { return symbol(ReporteSym.llaveIzquierda);
            }
          case 95: break;
          case 11: 
            { return symbol(ReporteSym.llaveDerecha);
            }
          case 96: break;
          case 12: 
            { return symbol(ReporteSym.parIzquierda);
            }
          case 97: break;
          case 13: 
            { return symbol(ReporteSym.parDerecha);
            }
          case 98: break;
          case 14: 
            { return symbol(ReporteSym.corcheteIzquierda);
            }
          case 99: break;
          case 15: 
            { return symbol(ReporteSym.corcheteDerecha);
            }
          case 100: break;
          case 16: 
            { return symbol(ReporteSym.puntoycoma);
            }
          case 101: break;
          case 17: 
            { return symbol(ReporteSym.coma);
            }
          case 102: break;
          case 18: 
            { return symbol(ReporteSym.dospuntos);
            }
          case 103: break;
          case 19: 
            { return symbol(ReporteSym.interrogacion);
            }
          case 104: break;
          case 20: 
            { return symbol(ReporteSym.mas);
            }
          case 105: break;
          case 21: 
            { return symbol(ReporteSym.menos);
            }
          case 106: break;
          case 22: 
            { return symbol(ReporteSym.modulo);
            }
          case 107: break;
          case 23: 
            { return symbol(ReporteSym.mayorque);
            }
          case 108: break;
          case 24: 
            { return symbol(ReporteSym.menorque);
            }
          case 109: break;
          case 25: 
            { return symbol(ReporteSym.igual);
            }
          case 110: break;
          case 26: 
            { return symbol(ReporteSym.not);
            }
          case 111: break;
          case 27: 
            { return symbol(ReporteSym.xor);
            }
          case 112: break;
          case 28: 
            { string.append( yytext() );
            }
          case 113: break;
          case 29: 
            { yybegin(YYINITIAL);
					   return symbol(ReporteSym.tstring, string.toString());
            }
          case 114: break;
          case 30: 
            { string.append('\\');
            }
          case 115: break;
          case 31: 
            { yybegin(YYINITIAL);
					   return symbol(ReporteSym.tchar, string.toString());
            }
          case 116: break;
          case 32: 
            { /* se ignora */
            }
          case 117: break;
          case 33: 
            { return symbol(ReporteSym.if_);
            }
          case 118: break;
          case 34: 
            { return symbol(ReporteSym.do_);
            }
          case 119: break;
          case 35: 
            { return symbol(ReporteSym.masmas);
            }
          case 120: break;
          case 36: 
            { return symbol(ReporteSym.menosmenos);
            }
          case 121: break;
          case 37: 
            { return symbol(ReporteSym.mayorigual);
            }
          case 122: break;
          case 38: 
            { return symbol(ReporteSym.menorigual);
            }
          case 123: break;
          case 39: 
            { return symbol(ReporteSym.igualigual);
            }
          case 124: break;
          case 40: 
            { return symbol(ReporteSym.diferente);
            }
          case 125: break;
          case 41: 
            { return symbol(ReporteSym.and);
            }
          case 126: break;
          case 42: 
            { return symbol(ReporteSym.or);
            }
          case 127: break;
          case 43: 
            { string.append('\r');
            }
          case 128: break;
          case 44: 
            { string.append('\t');
            }
          case 129: break;
          case 45: 
            { string.append('\n');
            }
          case 130: break;
          case 46: 
            { string.append('\"');
            }
          case 131: break;
          case 47: 
            { string.append('\'');
            }
          case 132: break;
          case 48: 
            { return symbol(ReporteSym.decimal, yytext());
            }
          case 133: break;
          case 49: 
            { return symbol(ReporteSym.pow_);
            }
          case 134: break;
          case 50: 
            { return symbol(ReporteSym.int_);
            }
          case 135: break;
          case 51: 
            { return symbol(ReporteSym.for_);
            }
          case 136: break;
          case 52: 
            { return symbol(ReporteSym.new_);
            }
          case 137: break;
          case 53: 
            { return symbol(ReporteSym.case_);
            }
          case 138: break;
          case 54: 
            { return symbol(ReporteSym.char_);
            }
          case 139: break;
          case 55: 
            { return symbol(ReporteSym.true_);
            }
          case 140: break;
          case 56: 
            { return symbol(ReporteSym.this_);
            }
          case 141: break;
          case 57: 
            { return symbol(ReporteSym.else_);
            }
          case 142: break;
          case 58: 
            { return symbol(ReporteSym.void_);
            }
          case 143: break;
          case 59: 
            { return symbol(ReporteSym.null_);
            }
          case 144: break;
          case 60: 
            { return symbol(ReporteSym.print_);
            }
          case 145: break;
          case 61: 
            { return symbol(ReporteSym.break_);
            }
          case 146: break;
          case 62: 
            { return symbol(ReporteSym.class_);
            }
          case 147: break;
          case 63: 
            { return symbol(ReporteSym.super_);
            }
          case 148: break;
          case 64: 
            { return symbol(ReporteSym.final_);
            }
          case 149: break;
          case 65: 
            { return symbol(ReporteSym.false_);
            }
          case 150: break;
          case 66: 
            { return symbol(ReporteSym.while_);
            }
          case 151: break;
          case 67: 
            { return symbol(ReporteSym.graph_);
            }
          case 152: break;
          case 68: 
            { return symbol(ReporteSym.public_);
            }
          case 153: break;
          case 69: 
            { return symbol(ReporteSym.import_);
            }
          case 154: break;
          case 70: 
            { return symbol(ReporteSym.return_);
            }
          case 155: break;
          case 71: 
            { return symbol(ReporteSym.double_);
            }
          case 156: break;
          case 72: 
            { return symbol(ReporteSym.static_);
            }
          case 157: break;
          case 73: 
            { return symbol(ReporteSym.switch_);
            }
          case 158: break;
          case 74: 
            { return symbol(ReporteSym.private_);
            }
          case 159: break;
          case 75: 
            { return symbol(ReporteSym.println_);
            }
          case 160: break;
          case 76: 
            { return symbol(ReporteSym.boolean_);
            }
          case 161: break;
          case 77: 
            { return symbol(ReporteSym.extends_);
            }
          case 162: break;
          case 78: 
            { return symbol(ReporteSym.default_);
            }
          case 163: break;
          case 79: 
            { return symbol(ReporteSym.continue_);
            }
          case 164: break;
          case 80: 
            { return symbol(ReporteSym.abstract_);
            }
          case 165: break;
          case 81: 
            { return symbol(ReporteSym.protected_);
            }
          case 166: break;
          case 82: 
            { return symbol(ReporteSym.read_file_);
            }
          case 167: break;
          case 83: 
            { return symbol(ReporteSym.printTabla_);
            }
          case 168: break;
          case 84: 
            { return symbol(ReporteSym.instanceof_);
            }
          case 169: break;
          case 85: 
            { return symbol(ReporteSym.write_file_);
            }
          case 170: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
