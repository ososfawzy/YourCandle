package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

/**
 * Created by osama on 6/22/2018.
 */

public class Bidi
{
    /**
     Character types for symbols from 0000 to 00FF.
     */
    public static String[] BaseTypes = new String[] {"BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "S", "B", "S", "WS", "B", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "B", "B", "B", "S", "WS", "ON", "ON", "ET", "ET", "ET", "ON", "ON", "ON", "ON", "ON", "ON", "CS", "ON", "CS", "ON", "EN", "EN", "EN", "EN", "EN", "EN", "EN", "EN", "EN", "EN", "ON", "ON", "ON", "ON", "ON", "ON", "ON", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "ON", "ON", "ON", "ON", "ON", "ON", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "ON", "ON", "ON", "ON", "BN", "BN", "BN", "BN", "BN", "BN", "B", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "BN", "CS", "ON", "ET", "ET", "ET", "ET", "ON", "ON", "ON", "ON", "L", "ON", "ON", "ON", "ON", "ON", "ET", "ET", "EN", "EN", "ON", "L", "ON", "ON", "ON", "EN", "L", "ON", "ON", "ON", "ON", "ON", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "ON", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "ON", "L", "L", "L", "L", "L", "L", "L", "L"};

    /**
     Character types for symbols from 0600 to 06FF
     */
    public static String[] ArabicTypes = new String[] {"AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "CS", "AL", "ON", "ON", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AN", "AN", "AN", "AN", "AN", "AN", "AN", "AN", "AN", "AN", "ET", "AN", "AN", "AL", "AL", "AL", "NSM", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "NSM", "ON", "NSM", "NSM", "NSM", "NSM", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL", "AL"};
    public static boolean IsOdd(int i)
    {
        return (i & 1) != 0;
    }

    public static boolean IsEven(int i)
    {
        return (i & 1) == 0;
    }

    public static int FindUnequal(String[] arr, int start, String value)
    {
        int j;
        int jj = arr.length;
        for (j = start; j < jj; ++j)
        {
            if (!arr[j].equals(value))
            {
                return j;
            }
        }
        return j;
    }

    public static void SetValues(String[] arr, int start, int end, String value)
    {
        for (int j = start; j < end; ++j)
        {
            arr[j] = value;
        }
    }

    public static char[] ReverseValues(char[] arr, int start, int end)
    {
        int j = end - 1;
        for (int i = start; i < j; ++i, --j)
        {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
    public static char MirrorGlyphs(char c)
    {
		/*
		 # BidiMirroring-1.txt
		 0028; 0029 # LEFT PARENTHESIS
		 0029; 0028 # RIGHT PARENTHESIS
		 003C; 003E # LESS-THAN SIGN
		 003E; 003C # GREATER-THAN SIGN
		 005B; 005D # LEFT SQUARE BRACKET
		 005D; 005B # RIGHT SQUARE BRACKET
		 007B; 007D # LEFT CURLY BRACKET
		 007D; 007B # RIGHT CURLY BRACKET
		 00AB; 00BB # LEFT-POINTING DOUBLE ANGLE QUOTATION MARK
		 00BB; 00AB # RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK
		 */
        switch (c)
        {
            case '(':
                return ')';
            case ')':
                return '(';
            case '<':
                return '>';
            case '>':
                return '<';
            case ']':
                return '[';
            case '[':
                return ']';
            case '}':
                return '{';
            case '{':
                return '}';
            case '\u00AB':
                return '\u00BB';
            case '\u00BB':
                return '\u00AB';
            default:
                return c;
        }
    }

    public static BidiResult BidiText(String str, int startLevel)
    {
        boolean isLtr = true;
        int strLength = str.length();
        if (strLength == 0)
        {
            return new BidiResult(str, false);
        }

        // get types, fill arrays

        char[] chars = new char[strLength];
        String[] types = new String[strLength];
        String[] oldtypes = new String[strLength];
        int numBidi = 0;

        for (int i = 0; i < strLength; ++i)
        {
            chars[i] = str.charAt(i);

            char charCode = str.charAt(i);
            String charType = "L";
            if (charCode <= 0x00ff)
            {
                charType = BaseTypes[charCode];
            }
            else if (0x0590 <= charCode && charCode <= 0x05f4)
            {
                charType = "R";
            }
            else if (0x0600 <= charCode && charCode <= 0x06ff)
            {
                charType = ArabicTypes[charCode & 0xff];
            }
            else if (0x0700 <= charCode && charCode <= 0x08AC)
            {
                charType = "AL";
            }

            if (charType.equals("R") || charType.equals("AL") || charType.equals("AN"))
            {
                numBidi++;
            }

            oldtypes[i] = types[i] = charType;
        }
// detect the bidi method
        //  if there are no rtl characters then no bidi needed
        //  if less than 30% chars are rtl then string is primarily ltr
        //  if more than 30% chars are rtl then string is primarily rtl
        if (numBidi == 0)
        {
            return new BidiResult(str, true);
        }

        if (startLevel == -1)
        {
            if ((strLength / numBidi) < 0.3)
            {
                startLevel = 0;
            }
            else
            {
                isLtr = false;
                startLevel = 1;
            }
        }

        int[] levels = new int[strLength];

        for (int i = 0; i < strLength; ++i)
        {
            levels[i] = startLevel;
        }

		/*
		 X1-X10: skip most of this, since we are NOT doing the embeddings.
		 */

        String e = IsOdd(startLevel) ? "R" : "L";
        String sor = e;
        String eor = sor;

		/*
		 W1. Examine each non-spacing mark (NSM) in the level run, and change the
		 type of the NSM to the type of the previous character. If the NSM is at the
		 start of the level run, it will get the type of sor.
		 */

        String lastType = sor;
        for (int i = 0; i < strLength; ++i)
        {
            if (types[i].equals("NSM"))
            {
                types[i] = lastType;
            }
            else
            {
                lastType = types[i];
            }
        }
/*
         W2. Search backwards from each instance of a European number until the
         first strong type (R, L, AL, or sor) is found.  If an AL is found, change
         the type of the European number to Arabic number.
         */

        lastType = sor;
        for (int i = 0; i < strLength; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            String t = types[i];
            if (t.equals("EN"))
            {
                types[i] = (lastType.equals("AL")) ? "AN" : "EN";
            }
            else if (t.equals("R") || t.equals("L") || t.equals("AL"))
            {
                lastType = t;
            }
        }

		/*
		 W3. Change all ALs to R.
		 */

        for (int i = 0; i < strLength; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            String t = types[i];
            if (t.equals("AL"))
            {
                types[i] = "R";
            }
        }

		/*
		 W4. A single European separator between two European numbers changes to a
		 European number. A single common separator between two numbers of the same
		 type changes to that type:
		 */

        for (int i = 1; i < strLength - 1; ++i)
        {
            if (types[i].equals("ES") && types[i - 1].equals("EN") && types[i + 1].equals("EN"))
            {
                types[i] = "EN";
            }
            if (types[i].equals("CS") && (types[i - 1].equals("EN") || types[i - 1].equals("AN")) && types[i + 1] == types[i - 1])
            {
                types[i] = types[i - 1];
            }
        }

		/*
		 W5. A sequence of European terminators adjacent to European numbers changes
		 to all European numbers:
		 */

        for (int i = 0; i < strLength; ++i)
        {
            if (types[i].equals("EN"))
            {
                // do before
                for (int j = i - 1; j >= 0; --j)
                {
                    if (!types[j].equals("ET"))
                    {
                        break;
                    }
                    types[j] = "EN";
                }
                // do after
                for (int j = i + 1; j < strLength; --j)
                {
                    if (!types[j].equals("ET"))
                    {
                        break;
                    }
                    types[j] = "EN";
                }
            }
        }

		/*
		 W6. Otherwise, separators and terminators change to Other Neutral:
		 */

        for (int i = 0; i < strLength; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            String t = types[i];
            if (t.equals("WS") || t.equals("ES") || t.equals("ET") || t.equals("CS"))
            {
                types[i] = "ON";
            }
        }

		/*
		 W7. Search backwards from each instance of a European number until the
		 first strong type (R, L, or sor) is found. If an L is found,  then change
		 the type of the European number to L.
		 */

        lastType = sor;
        for (int i = 0; i < strLength; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            String t = types[i];
            if (t.equals("EN"))
            {
                types[i] = (lastType.equals("L")) ? "L" : "EN";
            }
            else if (t.equals("R") || t.equals("L"))
            {
                lastType = t;
            }
        }

/*
         N1. A sequence of neutrals takes the direction of the surrounding strong
         text if the text on both sides has the same direction. European and Arabic
         numbers are treated as though they were R. Start-of-level-run (sor) and
         end-of-level-run (eor) are used at level run boundaries.
         */

        for (int i = 0; i < strLength; ++i)
        {
            if (types[i].equals("ON"))
            {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
                int end = FindUnequal(types, i + 1, "ON");
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
                String before = sor;
                if (i > 0)
                {
                    before = types[i - 1];
                }
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
                String after = eor;
                if (end + 1 < strLength)
                {
                    after = types[end + 1];
                }
                if (!before.equals("L"))
                {
                    before = "R";
                }
                if (!after.equals("L"))
                {
                    after = "R";
                }
                if (before == after)
                {
                    SetValues(types, i, end, before);
                }
                i = end - 1; // reset to end (-1 so next iteration is ok)
            }
        }

		/*
		 N2. Any remaining neutrals take the embedding direction.
		 */

        for (int i = 0; i < strLength; ++i)
        {
            if (types[i].equals("ON"))
            {
                types[i] = e;
            }
        }

		/*
		 I1. For all characters with an even (left-to-right) embedding direction,
		 those of type R go up one level and those of type AN or EN go up two
		 levels.
		 I2. For all characters with an odd (right-to-left) embedding direction,
		 those of type L, EN or AN go up one level.
		 */

        for (int i = 0; i < strLength; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            String t = types[i];
            if (IsEven(levels[i]))
            {
                if (t.equals("R"))
                {
                    levels[i] += 1;
                }
                else if (t.equals("AN") || t.equals("EN"))
                {
                    levels[i] += 2;
                }
            }
            else
            { // isOdd, so
                if (t.equals("L") || t.equals("AN") || t.equals("EN"))
                {
                    levels[i] += 1;
                }
            }
        }
/*
         L1. On each line, reset the embedding level of the following characters to
         the paragraph embedding level:

         segment separators,
         paragraph separators,
         any sequence of whitespace characters preceding a segment separator or
         paragraph separator, and any sequence of white space characters at the end
         of the line.
         */

        // don't bother as text is only single line

		/*
		 L2. From the highest level found in the text to the lowest odd level on
		 each line, reverse any contiguous sequence of characters that are at that
		 level or higher.
		 */

        // find highest level & lowest odd level

        int highestLevel = -1;
        int lowestOddLevel = 99;
        int ii = levels.length;
        for (int i = 0; i < ii; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            int level = levels[i];
            if (highestLevel < level)
            {
                highestLevel = level;
            }
            if (lowestOddLevel > level && IsOdd(level))
            {
                lowestOddLevel = level;
            }
        }

        // now reverse between those limits

        for (int level = highestLevel; level >= lowestOddLevel; --level)
        {
            // find segments to reverse
            int start = -1;
            ii = levels.length;
            for (int i = 0; i < ii; ++i)
            {
                if (levels[i] < level)
                {
                    if (start >= 0)
                    {
                        chars = ReverseValues(chars, start, i);
                        start = -1;
                    }
                }
                else if (start < 0)
                {
                    start = i;
                }
            }
            if (start >= 0)
            {
                chars = ReverseValues(chars, start, levels.length);
            }
        }

		/*
		 L3. Combining marks applied to a right-to-left base character will at this
		 point precede their base character. If the rendering engine expects them to
		 follow the base characters in the final display process, then the ordering
		 of the marks and the base character must be reversed.
		 */

        // don't bother for now

		/*
		 L4. A character that possesses the mirrored property as specified by
		 Section 4.7, Mirrored, must be depicted by a mirrored glyph if the resolved
		 directionality of that character is R.
		 */

        // don't mirror as characters are already mirrored in the pdf

        // Finally, return string

        String result = "";
        ii = chars.length;
        for (int i = 0; i < ii; ++i)
        {
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
            char ch = chars[i];
            if (ch != '<' && ch != '>')
            {
                result += ch;
            }
        }

        return new BidiResult(result, isLtr);
    }
}

