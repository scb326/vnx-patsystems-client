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


package com.patsystems.orders;

import patsystems.api.delphi.ByteArrayStasher;
import patsystems.api.delphi.StructDefinitions;

/**
 * <h1>TradeRequest</h1>
 * <p>Creates a byte buffer with all the order information to be passed to the JavaWrapper</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class TradeRequest
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";


    public byte[] buffer;

    /**
     * Constructor
     *
     * @param account
     * @param exchange
     * @param commodity
     * @param contract
     * @param orderType
     * @param openClose
     * @param side
     * @param price
     * @param lots
     */
    public TradeRequest(String account, String exchange, String commodity, String contract, String orderType,
                        String openClose, String side, String price, int lots)
    {
        buffer = new byte[StructDefinitions.SIZE_NEW_ORDER_STRUCT];
        ByteArrayStasher stasher = new ByteArrayStasher(buffer);

        stasher.append(account, StructDefinitions.SIZE_ACCOUNT_NAME);
        stasher.append(orderType, StructDefinitions.SIZE_ORDER_TYPE_NAME);
        stasher.append(exchange, StructDefinitions.SIZE_EXCHANGE_NAME);
        stasher.append(commodity, StructDefinitions.SIZE_COMMODITY_NAME);
        stasher.append(contract, StructDefinitions.SIZE_CONTRACT_NAME);
        stasher.append(side.charAt(0));    //  BUY_SELL_CODE
        stasher.append(price, StructDefinitions.SIZE_PRICE);
        stasher.append("0", StructDefinitions.SIZE_PRICE);
        stasher.append(lots);
        stasher.skipString(StructDefinitions.SIZE_ORDER_ID);
        stasher.append(openClose.charAt(0));
        stasher.skip(StructDefinitions.SIZE_INT);                          //  XREF
        stasher.skip(StructDefinitions.SIZE_INT);                        //  xrefp ???
        stasher.append("", StructDefinitions.SIZE_DATE);
        stasher.append(' ');                    //  TRIGGER
        stasher.append("", StructDefinitions.SIZE_REFERENCE);
        stasher.skipString(StructDefinitions.SIZE_ESA_REFERENCE);        // esa ref
        stasher.skip(StructDefinitions.SIZE_INT);                        // syoms priority
        stasher.skipString(StructDefinitions.SIZE_DATE);                 // syoms trigger date
        stasher.skipString(StructDefinitions.SIZE_TIME);                 // syoms trigger time
        stasher.append("", StructDefinitions.SIZE_BATCH_ID);
        stasher.append("", StructDefinitions.SIZE_BATCH_TYPE);
        stasher.skip(StructDefinitions.SIZE_INT);
        stasher.skipString(StructDefinitions.SIZE_BATCH_STATUS);
        stasher.skipString(StructDefinitions.SIZE_PARENT_ID);
        stasher.skip(StructDefinitions.SIZE_CHAR);                       // omi done for day
        stasher.skipString(StructDefinitions.SIZE_BIG_REFERENCE);
        stasher.skipString(StructDefinitions.SIZE_SENDER_LOCATION_ID);
        stasher.skipString(StructDefinitions.SIZE_RAW_PRICE);
        stasher.skipString(StructDefinitions.SIZE_RAW_PRICE2);
        stasher.skipString(StructDefinitions.SIZE_EXECUTION_ID);
        stasher.skipString(StructDefinitions.SIZE_CLIENT_ORDER_ID);
        stasher.append(' ');
        stasher.append(System.getProperty("com.patsystems.api.apimuser", "PATSGUI"), StructDefinitions.SIZE_APIM_USER);
        stasher.skipString(StructDefinitions.SIZE_YDSP_AUDIT);
        stasher.skipString(StructDefinitions.SIZE_ICS_NEARLEG_PRICE);
        stasher.skipString(StructDefinitions.SIZE_ICS_FARLEG_PRICE);
        stasher.append(0);
        stasher.append(0);
        stasher.append(' ');
        stasher.append("  ", StructDefinitions.SIZE_TICKET_TYPE);
        stasher.append("001", StructDefinitions.SIZE_TICKET_VERSION);
        stasher.append(" ", StructDefinitions.SIZE_EXCHANGE_FIELDS);
        stasher.append(" ", StructDefinitions.SIZE_BACK_OFFICE_ID_TEXT);
        stasher.append(" ", StructDefinitions.SIZE_BADGE_NUMBER_TEXT);

    }

}
