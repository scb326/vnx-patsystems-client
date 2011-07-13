/*************************************************************************************************
 ************************************** SOFTWARE DISCLAIMER **************************************
 *************************************************************************************************

 --------------------------------------IMPORTANT NOTICE-------------------------------------------

 1.	The Software is provided 'as is' without warranty of any kind, either express or implied,
    including, but not limited to, the implied warranties of fitness for a purpose, or the
    warranty of non-infringement.
 2.	Without limiting the foregoing, Patsystems makes no warranty that:
    (a)	the software will meet your requirements
    (b)	the software will be uninterrupted, timely, secure or error-free
    (c)	the results that may be obtained from the use of the software will be effective,
        accurate or reliable
    (d)	the quality of the software will meet your expectations
    (e)	any errors in the software obtained from Patsystems web site will be corrected.
 3.	Further the Software and its documentation made available:
    •	could include technical or other mistakes, inaccuracies or typographical errors.
        Patsystems may make changes to the software or documentation at its discretion.
    •	may be out of date, and Patsystems makes no commitment to update such materials.
 4.	Patsystems assumes no responsibility for errors or omissions in the software or
    documentation available from its web site and the software.
 5.	To the fullest extend permittable by law, in no event shall Patsystems be liable to you
    or kind, or any damages whatsoever, including, without limitation, those resulting from
    loss of use, data or profits, whether or not Patsystems has been advised of the possibility
    of such damages, and on any theory of liability, arising out of or in connection with the
    use of this software.
 6.	The use of the software is done at your own discretion and risk and with agreement that
    you will be solely responsible for any damage to your computer system or loss of data
    that results from such activities. No advice or information, whether oral or written,
    obtained by you from Patsystems or from Patsystems web site shall create any warranty
    for the software.

/*************************************************************************************************
 *************************************** COPYRIGHT NOTICE ****************************************
 *************************************************************************************************

 The disclosure, copying or distribution of the Software is prohibited and may be unlawful. The
 contents of the Software are copyright © Patsystems UK Limited. All rights are expressly reserved.

 Any content printed or otherwise may not be sold, licensed, transferred, copied or reproduced in
 whole or in part in any manner or in or on any media to any person without the prior written
 consent of Patsystems UK Limited.

 *************************************************************************************************/


package patsystems.api.delphi;


/**
 * <h1>Util</h1>
 * <p>This class will store miscellaneous methods for performing actions on structures.
 * Most of the methods are likely to run along side the byte array stasher and fetcher.</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class Util
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    /**
     * Gets a 32-bit <CODE>int</CODE> from a buffer using a small-endian
     * (LSB first) format.
     *
     * @param buffer The buffer containing the int
     * @param offset The offset of the int in the buffer
     * @return The int value from the buffer
     */
    public static int getInt(byte[] buffer, int offset)
    {
        return (((int) buffer[offset]) & 0xFF) |
                ((((int) buffer[offset + 1]) & 0xFF) << 8) +
                ((((int) buffer[offset + 2]) & 0xFF) << 16) +
                ((((int) buffer[offset + 3]) & 0xFF) << 24);
    }

    /**
     * <P>gets the string from a buffer</p>
     * @param buffer
     * @param start
     * @param count
     */
    public static String getString(byte[] buffer, int start, int count)
    {
        char[] c = new char[count];
        count += start;
        for (int i = start, j = 0; i < count && buffer[i] != 0; i++, j++)
            c[j] = (char) buffer[i];

        return new String(c).trim();
    }

    /**
     * puts a string into a buffer
     * @param value
     * @param buffer
     * @param offset
     * @param size
     * @return the location of the pointer
     */
    public static int putString(String value, byte[] buffer, int offset, int size)
    {
        byte[] tmp = value.getBytes();

        int len = offset + Math.min(value.length(), size);
        for (int i = 0; i < size; i++, offset++)
            buffer[offset] = offset < len ? tmp[i] : 0;

        return ++offset;
    }

    /**
     * <p>puts an int into a buffer</p>
     * @param value
     * @param buffer
     * @param offset
     * @return the location of the pointer
     */
    public static int putInt(int value, byte[] buffer, int offset)
    {
        buffer[offset++] = (byte) (value & 0xFF);
        buffer[offset++] = (byte) ((value >> 8) & 0xFF);
        buffer[offset++] = (byte) ((value >> 16) & 0xFF);
        buffer[offset++] = (byte) ((value >> 24) & 0xFF);
        return offset;
    }

}
