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
 * <h1>ByteArrayStasher</h1>
 * <p>ByteArrayStasher populates a byte buffer with the information to be passed from the Java Demo App
 * to the JavaWrapper. The size and structure of the buffer is determined by the nature of the message
 * being sent to the JavaWrapper</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */

public class ByteArrayStasher
{

    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    int offset = 0;
    byte[] buffer;

    /**
     * constructor
     *
     * @param buffer
     */
    public ByteArrayStasher(byte[] buffer)
    {
        this.buffer = buffer;
        this.offset = 0;
    }

    /**
     * appends a string of a specified length to the buffer
     *
     * @param value  string
     * @param length int
     * @return a ByteArrayStasher
     */
    public ByteArrayStasher append(String value, int length)
    {
        if (value != null)
        {
            offset = Util.putString(value, buffer, offset, length);
        }
        else
        {
            offset += length + 1;
        }

        return this;
    }

    /**
     * appends an int to the buffer
     *
     * @param value int
     */
    public ByteArrayStasher append(int value)
    {
        offset = Util.putInt(value, buffer, offset);
        return this;
    }

    /**
     * appends a byte to the buffer
     *
     * @param b byte
     */
    public ByteArrayStasher append(byte b)
    {
        buffer[offset++] = b;
        return this;
    }

    /**
     * appends a char to the buffer
     *
     * @param c char
     */
    public ByteArrayStasher append(char c)
    {
        return append((byte) c);
    }

    /**
     * appends a byte array to the buffer
     *
     * @param b byte[]
     */
    public ByteArrayStasher append(byte[] b)
    {
        return append(b, true);
    }

    /**
     * appends a byte array to the buffer and null terminates if set to true
     *
     * @param b              byte[]
     * @param zeroTerminated boolean
     */
    public ByteArrayStasher append(byte[] b, boolean zeroTerminated)
    {
        System.arraycopy(b, 0, buffer, offset, b.length);
        offset += b.length;
        if (zeroTerminated)
        {
            return append((byte) 0);
        }
        return this;
    }

    /**
     * moves the buffer pointer along by the specified number of bytes
     *
     * @param n int
     */
    public ByteArrayStasher skip(int n)
    {
        offset += n;
        return this;
    }

    /**
     * moves the buffer pointer along by the number of chars in the string
     *
     * @param n int
     */
    public ByteArrayStasher skipString(int n)
    {
        return skip(++n);
    }
}

