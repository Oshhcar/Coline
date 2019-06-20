/* The following code was generated by JFlex 1.6.1 */

package analizador;

import java_cup.runtime.Symbol;
import java.util.ArrayList;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>lexico.jflex</tt>
 */
public class Lexico implements java_cup.runtime.Scanner {

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
    "\11\0\1\7\1\6\1\67\1\7\1\5\22\0\1\7\1\62\1\40"+
    "\2\0\1\56\1\63\1\41\1\44\1\45\1\11\1\54\1\51\1\55"+
    "\1\2\1\10\12\1\1\52\1\50\1\60\1\61\1\57\1\53\1\0"+
    "\23\3\1\34\6\3\1\46\1\66\1\47\1\65\1\4\1\0\1\26"+
    "\1\14\1\17\1\24\1\23\1\30\1\3\1\33\1\16\1\3\1\35"+
    "\1\15\1\37\1\31\1\21\1\12\1\3\1\20\1\27\1\22\1\13"+
    "\1\25\1\36\1\32\2\3\1\42\1\64\1\43\7\0\1\67\53\0"+
    "\1\3\21\0\1\3\u1f54\0\1\3\17\0\1\67\1\67\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

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
    "\16\4\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\2\1\1\33\2\34\1\35"+
    "\1\36\2\34\1\37\1\36\1\0\1\40\1\0\1\41"+
    "\1\42\4\4\1\43\12\4\1\44\13\4\1\45\1\46"+
    "\1\47\1\50\1\51\1\52\1\53\1\54\1\55\1\56"+
    "\1\34\1\57\1\60\1\61\1\62\1\34\1\63\1\64"+
    "\2\40\1\0\5\4\1\65\21\4\1\66\2\4\1\67"+
    "\1\4\2\34\2\0\11\4\1\70\1\71\2\4\1\72"+
    "\1\73\3\4\1\74\5\4\1\75\1\4\1\34\1\40"+
    "\2\34\1\40\1\34\1\0\1\40\2\4\1\76\1\4"+
    "\1\77\2\4\1\100\11\4\1\101\1\102\1\103\1\34"+
    "\1\40\1\34\1\40\1\104\5\4\1\105\1\4\1\106"+
    "\2\4\1\107\2\4\1\110\1\111\1\112\1\113\2\4"+
    "\1\114\2\4\1\115\1\116\3\4\1\117\1\4\1\120"+
    "\1\4\1\121\1\122\1\123";

  private static int [] zzUnpackAction() {
    int [] result = new int[233];
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
    "\0\0\0\70\0\160\0\250\0\340\0\250\0\u0118\0\u0150"+
    "\0\250\0\u0188\0\u01c0\0\u01f8\0\u0230\0\u0268\0\u02a0\0\u02d8"+
    "\0\u0310\0\u0348\0\u0380\0\u03b8\0\u03f0\0\u0428\0\u0460\0\u0498"+
    "\0\u04d0\0\250\0\250\0\250\0\250\0\250\0\250\0\250"+
    "\0\250\0\250\0\250\0\250\0\250\0\u0508\0\u0540\0\250"+
    "\0\u0578\0\u05b0\0\u05e8\0\u0620\0\u0658\0\u0690\0\250\0\u06c8"+
    "\0\u0700\0\250\0\u0738\0\u0770\0\u07a8\0\250\0\u07e0\0\u0818"+
    "\0\u0850\0\u0888\0\250\0\250\0\u08c0\0\u08f8\0\u0930\0\u0968"+
    "\0\u0118\0\u09a0\0\u09d8\0\u0a10\0\u0a48\0\u0a80\0\u0ab8\0\u0af0"+
    "\0\u0b28\0\u0b60\0\u0b98\0\u0bd0\0\u0c08\0\u0c40\0\u0c78\0\u0cb0"+
    "\0\u0ce8\0\u0d20\0\u0d58\0\u0d90\0\u0dc8\0\u0e00\0\u0e38\0\250"+
    "\0\250\0\250\0\250\0\250\0\250\0\250\0\250\0\250"+
    "\0\250\0\u0e70\0\250\0\250\0\250\0\250\0\u0ea8\0\250"+
    "\0\u0818\0\u0ee0\0\250\0\u0f18\0\u0f50\0\u0f88\0\u0fc0\0\u0ff8"+
    "\0\u1030\0\u0118\0\u1068\0\u10a0\0\u10d8\0\u1110\0\u1148\0\u1180"+
    "\0\u11b8\0\u11f0\0\u1228\0\u1260\0\u1298\0\u12d0\0\u1308\0\u1340"+
    "\0\u1378\0\u13b0\0\u13e8\0\u0118\0\u1420\0\u1458\0\u0118\0\u1490"+
    "\0\u14c8\0\u1500\0\u1538\0\u1570\0\u15a8\0\u15e0\0\u1618\0\u1650"+
    "\0\u1688\0\u16c0\0\u16f8\0\u1730\0\u1768\0\u0118\0\u0118\0\u17a0"+
    "\0\u17d8\0\u0118\0\u0118\0\u1810\0\u1848\0\u1880\0\u0118\0\u18b8"+
    "\0\u18f0\0\u1928\0\u1960\0\u1998\0\u0118\0\u19d0\0\u1a08\0\u06c8"+
    "\0\u1a40\0\u1a78\0\u0770\0\u1ab0\0\u1ae8\0\u1ae8\0\u1b20\0\u1b58"+
    "\0\u1b90\0\u1bc8\0\u0118\0\u1c00\0\u1c38\0\u0118\0\u1c70\0\u1ca8"+
    "\0\u1ce0\0\u1d18\0\u1d50\0\u1d88\0\u1dc0\0\u1df8\0\u1e30\0\u0118"+
    "\0\u0118\0\u0118\0\u1e68\0\u1e68\0\u1ea0\0\u1ea0\0\u0118\0\u1ed8"+
    "\0\u1f10\0\u1f48\0\u1f80\0\u1fb8\0\u0118\0\u1ff0\0\u0118\0\u2028"+
    "\0\u2060\0\u0118\0\u2098\0\u20d0\0\u0118\0\u0118\0\u0118\0\u0118"+
    "\0\u2108\0\u2140\0\u0118\0\u2178\0\u21b0\0\u0118\0\u0118\0\u21e8"+
    "\0\u2220\0\u2258\0\u0118\0\u2290\0\u0118\0\u22c8\0\u0118\0\u0118"+
    "\0\u0118";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[233];
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
    "\1\57\1\4\1\0\5\60\2\0\1\60\1\61\27\60"+
    "\1\62\25\60\1\63\1\60\5\64\2\0\1\64\1\65"+
    "\30\64\1\66\24\64\1\67\1\64\71\0\1\5\1\70"+
    "\66\0\1\7\1\0\2\7\5\0\26\7\36\0\1\11"+
    "\71\0\1\71\1\72\47\0\1\73\67\0\1\74\7\0"+
    "\1\7\1\0\2\7\5\0\1\7\1\75\4\7\1\76"+
    "\17\7\31\0\1\7\1\0\2\7\5\0\6\7\1\77"+
    "\1\100\16\7\31\0\1\7\1\0\2\7\5\0\16\7"+
    "\1\101\1\102\5\7\1\103\31\0\1\7\1\0\2\7"+
    "\5\0\3\7\1\104\3\7\1\105\4\7\1\106\4\7"+
    "\1\107\4\7\31\0\1\7\1\0\2\7\5\0\11\7"+
    "\1\110\14\7\31\0\1\7\1\0\2\7\5\0\6\7"+
    "\1\111\17\7\31\0\1\7\1\0\2\7\5\0\3\7"+
    "\1\112\14\7\1\113\5\7\31\0\1\7\1\0\2\7"+
    "\5\0\7\7\1\114\1\7\1\115\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\7\7\1\116\16\7\31\0\1\7"+
    "\1\0\2\7\5\0\2\7\1\117\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\120\13\7\1\121\1\7"+
    "\31\0\1\7\1\0\2\7\5\0\4\7\1\122\2\7"+
    "\1\123\4\7\1\124\11\7\31\0\1\7\1\0\2\7"+
    "\5\0\1\7\1\125\7\7\1\126\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\21\7\1\127\4\7\104\0\1\130"+
    "\4\0\1\131\63\0\1\132\3\0\1\133\67\0\1\134"+
    "\67\0\1\135\67\0\1\136\67\0\1\137\71\0\1\140"+
    "\70\0\1\141\3\0\5\60\2\0\31\60\1\0\25\60"+
    "\1\0\6\60\2\0\1\60\1\71\1\142\26\60\1\0"+
    "\25\60\1\0\1\60\20\0\1\143\1\0\1\144\6\0"+
    "\1\145\6\0\1\146\27\0\5\64\2\0\32\64\1\0"+
    "\24\64\1\0\6\64\2\0\1\64\1\71\1\147\27\64"+
    "\1\0\24\64\1\0\1\64\20\0\1\143\1\0\1\144"+
    "\6\0\1\145\7\0\1\150\27\0\1\151\66\0\5\71"+
    "\1\152\1\153\61\71\11\72\1\154\56\72\1\0\1\7"+
    "\1\0\2\7\5\0\2\7\1\155\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\4\7\1\156\2\7\1\157\16\7"+
    "\31\0\1\7\1\0\2\7\5\0\11\7\1\160\14\7"+
    "\31\0\1\7\1\0\2\7\5\0\7\7\1\161\16\7"+
    "\31\0\1\7\1\0\2\7\5\0\10\7\1\162\15\7"+
    "\31\0\1\7\1\0\2\7\5\0\1\163\25\7\31\0"+
    "\1\7\1\0\2\7\5\0\14\7\1\164\11\7\31\0"+
    "\1\7\1\0\2\7\5\0\17\7\1\165\6\7\31\0"+
    "\1\7\1\0\2\7\5\0\15\7\1\166\10\7\31\0"+
    "\1\7\1\0\2\7\5\0\14\7\1\167\11\7\31\0"+
    "\1\7\1\0\2\7\5\0\10\7\1\170\3\7\1\171"+
    "\11\7\31\0\1\7\1\0\2\7\5\0\1\7\1\172"+
    "\24\7\31\0\1\7\1\0\2\7\5\0\15\7\1\173"+
    "\10\7\31\0\1\7\1\0\2\7\5\0\10\7\1\174"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\1\7\1\175"+
    "\24\7\31\0\1\7\1\0\2\7\5\0\16\7\1\176"+
    "\7\7\31\0\1\7\1\0\2\7\5\0\4\7\1\177"+
    "\21\7\31\0\1\7\1\0\2\7\5\0\15\7\1\200"+
    "\10\7\31\0\1\7\1\0\2\7\5\0\14\7\1\201"+
    "\11\7\31\0\1\7\1\0\2\7\5\0\4\7\1\202"+
    "\21\7\31\0\1\7\1\0\2\7\5\0\17\7\1\203"+
    "\6\7\31\0\1\7\1\0\2\7\5\0\6\7\1\204"+
    "\17\7\31\0\1\7\1\0\2\7\5\0\3\7\1\205"+
    "\22\7\31\0\1\7\1\0\2\7\5\0\3\7\1\206"+
    "\22\7\31\0\1\7\1\0\2\7\5\0\24\7\1\207"+
    "\1\7\31\0\1\7\1\0\2\7\5\0\4\7\1\210"+
    "\21\7\30\0\5\142\2\72\2\142\1\211\26\142\1\72"+
    "\25\142\1\72\1\142\5\147\2\72\2\147\1\212\27\147"+
    "\1\72\24\147\1\72\1\147\6\0\1\153\61\0\10\213"+
    "\1\153\1\214\56\213\1\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\215\22\7\31\0\1\7\1\0\2\7\5\0"+
    "\13\7\1\216\3\7\1\217\6\7\31\0\1\7\1\0"+
    "\2\7\5\0\10\7\1\220\15\7\31\0\1\7\1\0"+
    "\2\7\5\0\14\7\1\221\11\7\31\0\1\7\1\0"+
    "\2\7\5\0\3\7\1\222\22\7\31\0\1\7\1\0"+
    "\2\7\5\0\7\7\1\223\16\7\31\0\1\7\1\0"+
    "\2\7\5\0\15\7\1\224\10\7\31\0\1\7\1\0"+
    "\2\7\5\0\10\7\1\225\15\7\31\0\1\7\1\0"+
    "\2\7\5\0\11\7\1\226\14\7\31\0\1\7\1\0"+
    "\2\7\5\0\6\7\1\227\17\7\31\0\1\7\1\0"+
    "\2\7\5\0\1\7\1\230\24\7\31\0\1\7\1\0"+
    "\2\7\5\0\12\7\1\231\13\7\31\0\1\7\1\0"+
    "\2\7\5\0\11\7\1\232\14\7\31\0\1\7\1\0"+
    "\2\7\5\0\11\7\1\233\14\7\31\0\1\7\1\0"+
    "\2\7\5\0\11\7\1\234\14\7\31\0\1\7\1\0"+
    "\2\7\5\0\2\7\1\235\23\7\31\0\1\7\1\0"+
    "\2\7\5\0\14\7\1\236\11\7\31\0\1\7\1\0"+
    "\2\7\5\0\12\7\1\237\13\7\31\0\1\7\1\0"+
    "\2\7\5\0\10\7\1\240\15\7\31\0\1\7\1\0"+
    "\2\7\5\0\10\7\1\241\15\7\31\0\1\7\1\0"+
    "\2\7\5\0\10\7\1\242\15\7\31\0\1\7\1\0"+
    "\2\7\5\0\14\7\1\243\11\7\31\0\1\7\1\0"+
    "\2\7\5\0\15\7\1\244\10\7\31\0\1\7\1\0"+
    "\2\7\5\0\3\7\1\245\22\7\31\0\1\7\1\0"+
    "\2\7\5\0\3\7\1\246\22\7\30\0\5\247\2\213"+
    "\1\247\1\250\1\251\26\247\1\213\25\247\1\213\1\247"+
    "\5\252\2\213\1\252\1\253\1\254\27\252\1\213\24\252"+
    "\1\213\1\252\10\72\1\255\1\154\66\72\1\256\1\154"+
    "\56\72\1\0\1\7\1\0\2\7\5\0\4\7\1\257"+
    "\21\7\31\0\1\7\1\0\2\7\5\0\14\7\1\260"+
    "\11\7\31\0\1\7\1\0\2\7\5\0\10\7\1\261"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\11\7\1\262"+
    "\14\7\31\0\1\7\1\0\2\7\5\0\23\7\1\263"+
    "\2\7\31\0\1\7\1\0\2\7\5\0\11\7\1\264"+
    "\14\7\31\0\1\7\1\0\2\7\5\0\6\7\1\265"+
    "\17\7\31\0\1\7\1\0\2\7\5\0\15\7\1\266"+
    "\10\7\31\0\1\7\1\0\2\7\5\0\4\7\1\267"+
    "\21\7\31\0\1\7\1\0\2\7\5\0\6\7\1\270"+
    "\17\7\31\0\1\7\1\0\1\7\1\271\5\0\26\7"+
    "\31\0\1\7\1\0\2\7\5\0\17\7\1\272\6\7"+
    "\31\0\1\7\1\0\2\7\5\0\3\7\1\273\22\7"+
    "\31\0\1\7\1\0\2\7\5\0\1\7\1\274\24\7"+
    "\31\0\1\7\1\0\2\7\5\0\6\7\1\275\17\7"+
    "\31\0\1\7\1\0\2\7\5\0\4\7\1\276\21\7"+
    "\31\0\1\7\1\0\2\7\5\0\5\7\1\277\20\7"+
    "\31\0\1\7\1\0\2\7\5\0\3\7\1\300\22\7"+
    "\31\0\1\7\1\0\2\7\5\0\11\7\1\301\14\7"+
    "\31\0\1\7\1\0\2\7\5\0\11\7\1\302\14\7"+
    "\30\0\5\142\2\72\1\142\1\303\1\211\26\142\1\72"+
    "\25\142\1\72\6\142\2\72\1\142\1\304\1\211\26\142"+
    "\1\72\25\142\1\72\1\142\5\147\2\72\1\147\1\305"+
    "\1\212\27\147\1\72\24\147\1\72\6\147\2\72\1\147"+
    "\1\306\1\212\27\147\1\72\24\147\1\72\1\147\10\0"+
    "\1\213\60\0\1\7\1\0\2\7\5\0\5\7\1\307"+
    "\20\7\31\0\1\7\1\0\2\7\5\0\10\7\1\310"+
    "\15\7\31\0\1\7\1\0\2\7\5\0\3\7\1\311"+
    "\16\7\1\312\3\7\31\0\1\7\1\0\2\7\5\0"+
    "\5\7\1\313\20\7\31\0\1\7\1\0\2\7\5\0"+
    "\14\7\1\314\11\7\31\0\1\7\1\0\2\7\5\0"+
    "\10\7\1\315\15\7\31\0\1\7\1\0\2\7\5\0"+
    "\17\7\1\316\6\7\31\0\1\7\1\0\2\7\5\0"+
    "\17\7\1\317\6\7\31\0\1\7\1\0\2\7\5\0"+
    "\16\7\1\320\7\7\31\0\1\7\1\0\2\7\5\0"+
    "\12\7\1\321\13\7\31\0\1\7\1\0\2\7\5\0"+
    "\11\7\1\322\14\7\31\0\1\7\1\0\2\7\5\0"+
    "\3\7\1\323\22\7\31\0\1\7\1\0\2\7\5\0"+
    "\14\7\1\324\11\7\31\0\1\7\1\0\2\7\5\0"+
    "\5\7\1\325\20\7\31\0\1\7\1\0\2\7\5\0"+
    "\21\7\1\326\4\7\30\0\5\60\2\0\1\60\1\247"+
    "\27\60\1\0\25\60\1\0\1\60\5\64\2\0\1\64"+
    "\1\252\30\64\1\0\24\64\1\0\1\64\1\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\327\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\17\7\1\330\6\7\31\0\1\7"+
    "\1\0\2\7\5\0\14\7\1\331\11\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\332\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\17\7\1\333\6\7\31\0\1\7"+
    "\1\0\2\7\5\0\1\7\1\334\24\7\31\0\1\7"+
    "\1\0\2\7\5\0\4\7\1\335\21\7\31\0\1\7"+
    "\1\0\2\7\5\0\15\7\1\336\10\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\337\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\5\7\1\340\20\7\31\0\1\7"+
    "\1\0\2\7\5\0\2\7\1\341\23\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\342\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\343\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\3\7\1\344\22\7\31\0\1\7"+
    "\1\0\2\7\5\0\10\7\1\345\15\7\31\0\1\7"+
    "\1\0\2\7\5\0\3\7\1\346\22\7\31\0\1\7"+
    "\1\0\2\7\5\0\12\7\1\347\13\7\31\0\1\7"+
    "\1\0\2\7\5\0\11\7\1\350\14\7\31\0\1\7"+
    "\1\0\2\7\5\0\14\7\1\351\11\7\30\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[8960];
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
    "\3\0\1\11\1\1\1\11\2\1\1\11\20\1\14\11"+
    "\2\1\1\11\6\1\1\11\2\1\1\11\3\1\1\11"+
    "\1\1\1\0\1\1\1\0\2\11\33\1\12\11\1\1"+
    "\4\11\1\1\1\11\2\1\1\11\1\0\36\1\2\0"+
    "\40\1\1\0\74\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[233];
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
        
        private String valorError = "";
        private int columnaError = 0;
        private boolean isError = false;

        private ArrayList<ErrorC> errores = new ArrayList<>();

        public ArrayList<ErrorC> getErrores(){
            return this.errores;
        }

        public void addError(){
            if(this.isError){
                ErrorC error = new ErrorC();
                error.setTipo("Léxico");
                error.setLinea(yyline+1);
                error.setColumna(this.columnaError);
                error.setValor(this.valorError);
                error.setDescripcion("Carácter no reconocido.");
                this.errores.add(error);
                this.isError = false;
                this.columnaError = 0;
                this.valorError = "";
            }
            
        }
        
	private Symbol symbol(int type) {
                this.addError();
		return new Symbol(type, yyline+1, yycolumn+1);
	}	
  
	private Symbol symbol(int type, Object value) {
                this.addError();
		return new Symbol(type, yyline+1, yycolumn+1, value);
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
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
          { return new java_cup.runtime.Symbol(Sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { if(!this.isError){
                        this.isError = true;
                        this.columnaError = yycolumn+1;
                    }
                    this.valorError += yytext();
            }
          case 84: break;
          case 2: 
            { return symbol(Sym.entero, yytext());
            }
          case 85: break;
          case 3: 
            { return symbol(Sym.punto);
            }
          case 86: break;
          case 4: 
            { return symbol(Sym.id, yytext());
            }
          case 87: break;
          case 5: 
            { if(this.isError){this.valorError += " ";}
            }
          case 88: break;
          case 6: 
            { return symbol(Sym.diagonal);
            }
          case 89: break;
          case 7: 
            { return symbol(Sym.asterisco);
            }
          case 90: break;
          case 8: 
            { string.setLength(0); yybegin(STRING);
            }
          case 91: break;
          case 9: 
            { string.setLength(0); yybegin(CHAR);
            }
          case 92: break;
          case 10: 
            { return symbol(Sym.llaveIzquierda);
            }
          case 93: break;
          case 11: 
            { return symbol(Sym.llaveDerecha);
            }
          case 94: break;
          case 12: 
            { return symbol(Sym.parIzquierda);
            }
          case 95: break;
          case 13: 
            { return symbol(Sym.parDerecha);
            }
          case 96: break;
          case 14: 
            { return symbol(Sym.corcheteIzquierda);
            }
          case 97: break;
          case 15: 
            { return symbol(Sym.corcheteDerecha);
            }
          case 98: break;
          case 16: 
            { return symbol(Sym.puntoycoma);
            }
          case 99: break;
          case 17: 
            { return symbol(Sym.coma);
            }
          case 100: break;
          case 18: 
            { return symbol(Sym.dospuntos);
            }
          case 101: break;
          case 19: 
            { return symbol(Sym.interrogacion);
            }
          case 102: break;
          case 20: 
            { return symbol(Sym.mas);
            }
          case 103: break;
          case 21: 
            { return symbol(Sym.menos);
            }
          case 104: break;
          case 22: 
            { return symbol(Sym.modulo);
            }
          case 105: break;
          case 23: 
            { return symbol(Sym.mayorque);
            }
          case 106: break;
          case 24: 
            { return symbol(Sym.menorque);
            }
          case 107: break;
          case 25: 
            { return symbol(Sym.igual);
            }
          case 108: break;
          case 26: 
            { return symbol(Sym.not);
            }
          case 109: break;
          case 27: 
            { return symbol(Sym.xor);
            }
          case 110: break;
          case 28: 
            { string.append( yytext() );
            }
          case 111: break;
          case 29: 
            { yybegin(YYINITIAL);
					   return symbol(Sym.tstring, string.toString());
            }
          case 112: break;
          case 30: 
            { string.append('\\');
            }
          case 113: break;
          case 31: 
            { yybegin(YYINITIAL);
					   return symbol(Sym.tchar, string.toString());
            }
          case 114: break;
          case 32: 
            { /* se ignora */
            }
          case 115: break;
          case 33: 
            { return symbol(Sym.diagonaligual);
            }
          case 116: break;
          case 34: 
            { return symbol(Sym.porigual);
            }
          case 117: break;
          case 35: 
            { return symbol(Sym.if_);
            }
          case 118: break;
          case 36: 
            { return symbol(Sym.do_);
            }
          case 119: break;
          case 37: 
            { return symbol(Sym.masmas);
            }
          case 120: break;
          case 38: 
            { return symbol(Sym.masigual);
            }
          case 121: break;
          case 39: 
            { return symbol(Sym.menosmenos);
            }
          case 122: break;
          case 40: 
            { return symbol(Sym.menosigual);
            }
          case 123: break;
          case 41: 
            { return symbol(Sym.mayorigual);
            }
          case 124: break;
          case 42: 
            { return symbol(Sym.menorigual);
            }
          case 125: break;
          case 43: 
            { return symbol(Sym.igualigual);
            }
          case 126: break;
          case 44: 
            { return symbol(Sym.diferente);
            }
          case 127: break;
          case 45: 
            { return symbol(Sym.and);
            }
          case 128: break;
          case 46: 
            { return symbol(Sym.or);
            }
          case 129: break;
          case 47: 
            { string.append('\r');
            }
          case 130: break;
          case 48: 
            { string.append('\t');
            }
          case 131: break;
          case 49: 
            { string.append('\n');
            }
          case 132: break;
          case 50: 
            { string.append('\"');
            }
          case 133: break;
          case 51: 
            { string.append('\'');
            }
          case 134: break;
          case 52: 
            { return symbol(Sym.decimal, yytext());
            }
          case 135: break;
          case 53: 
            { return symbol(Sym.int_);
            }
          case 136: break;
          case 54: 
            { return symbol(Sym.for_);
            }
          case 137: break;
          case 55: 
            { return symbol(Sym.new_);
            }
          case 138: break;
          case 56: 
            { return symbol(Sym.case_);
            }
          case 139: break;
          case 57: 
            { return symbol(Sym.char_);
            }
          case 140: break;
          case 58: 
            { return symbol(Sym.true_);
            }
          case 141: break;
          case 59: 
            { return symbol(Sym.else_);
            }
          case 142: break;
          case 60: 
            { return symbol(Sym.void_);
            }
          case 143: break;
          case 61: 
            { return symbol(Sym.null_);
            }
          case 144: break;
          case 62: 
            { return symbol(Sym.print_);
            }
          case 145: break;
          case 63: 
            { return symbol(Sym.break_);
            }
          case 146: break;
          case 64: 
            { return symbol(Sym.class_);
            }
          case 147: break;
          case 65: 
            { return symbol(Sym.final_);
            }
          case 148: break;
          case 66: 
            { return symbol(Sym.false_);
            }
          case 149: break;
          case 67: 
            { return symbol(Sym.while_);
            }
          case 150: break;
          case 68: 
            { return symbol(Sym.public_);
            }
          case 151: break;
          case 69: 
            { return symbol(Sym.import_);
            }
          case 152: break;
          case 70: 
            { return symbol(Sym.return_);
            }
          case 153: break;
          case 71: 
            { return symbol(Sym.double_);
            }
          case 154: break;
          case 72: 
            { return symbol(Sym.static_);
            }
          case 155: break;
          case 73: 
            { return symbol(Sym.switch_);
            }
          case 156: break;
          case 74: 
            { return symbol(Sym.private_);
            }
          case 157: break;
          case 75: 
            { return symbol(Sym.println_);
            }
          case 158: break;
          case 76: 
            { return symbol(Sym.boolean_);
            }
          case 159: break;
          case 77: 
            { return symbol(Sym.extends_);
            }
          case 160: break;
          case 78: 
            { return symbol(Sym.default_);
            }
          case 161: break;
          case 79: 
            { return symbol(Sym.continue_);
            }
          case 162: break;
          case 80: 
            { return symbol(Sym.abstract_);
            }
          case 163: break;
          case 81: 
            { return symbol(Sym.protected_);
            }
          case 164: break;
          case 82: 
            { return symbol(Sym.read_file_);
            }
          case 165: break;
          case 83: 
            { return symbol(Sym.printTabla_);
            }
          case 166: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
