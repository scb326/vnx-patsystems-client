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


package com.patsystems;

/**
 * <h1>APICallbacks</h1>
 * <p>This class holds all the callback constants that will be received from the JavaWrapper
 * and handled on the callback thread</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public interface APICallbacks
{

    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    /**
     * Host Link Status Change message ID.
     */
    public static final int MID_HOST_LINK_CHANGE = 1;

    /**
     * Price Link Status Change message ID.
     */
    public static final int MID_PRICE_LINK_CHANGE = 2;

    /**
     * Logon Status message ID.
     */
    public static final int MID_LOGON_STATUS = 3;

    /**
     * User Message message ID.
     */
    public final static int MID_MESSAGE = 4;

    /**
     * Order message ID.
     */
    public static final int MID_ORDER = 5;

    /**
     * End of Day message ID.
     */
    public static final int MID_FORCED_LOGOUT = 6;

    /**
     * Download Complete message ID.
     */
    public static final int MID_DOWNLOAD_COMPLETE = 7;

    /**
     * Price Change message ID.
     */
    public static final int MID_PRICE = 8;

    /**
     * Fill message ID.
     */
    public static final int MID_FILL = 9;

    /**
     * Status Update message ID.
     */
    public static final int MID_STATUS = 10;

    /**
     * Contract Added message ID.
     */
    public static final int MID_CONTRACT_ADDED = 11;

    /**
     * Contract Deleted message ID.
     */
    public static final int MID_CONTRACT_DELETED = 12;

    /**
     * Exchange Rate Updated message ID.
     */
    public static final int MID_EXCHANGE_RATE = 13;

    /**
     * Connectivity Status Update message ID.
     */
    public static final int MID_CONNECTIVITY_STATUS = 14;

    /**
     * Order Cancellation Timeout message ID.
     */
    public static final int MID_ORDER_CANCEL_FAILURE_ID = 15;

    /**
     * At Best message ID.
     */
    public static final int MID_AT_BEST_ID = 16;

    /**
     * Memory warning message ID.
     */
    public static final int MID_MEMORY_WARNING = 18;

    /**
     * Subscriber Depth message ID.
     */
    public static final int MID_SUBSCRIBER_DEPTH = 19;

    /**
     * DOM update message ID.
     */
    public static final int MID_DOM_UPDATE = 21;

    /**
     * Settlement Price message ID.
     */
    public static final int MID_SETTLEMENT_PRICE = 22;

    /**
     * Strategy creation successfullyReceived ID.
     */
    public static final int MID_STRATEGY_CREATION_RECEIVED = 23;

    /**
     * Strategy creation failure ID.
     */
    public static final int MID_STRATEGY_CREATION_FAILURE = 24;

    /**
     * Generic Price message ID.
     */
    public static final int MID_GENERIC_PRICE = 26;

    /**
     * Price blank message ID
     */
    public static final int MID_BLANK_PRICE = 27;

    /**
     * Order Queued Timeout ID.
     */
    public static final int MID_ORDER_QUEUED_TIMEOUT = 28;

    /**
     * Order Sent Timeout ID.
     */
    public static final int MID_ORDER_SENT_TIMEOUT = 29;

    /**
     * Order Book reset ID.
     */
    public static final int MID_RESET_ORDERBOOK = 30;

    /**
     * Exception/Error ID (Internal).
     */
    public static final int MID_ERROR = -1;

    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQ = 100;

    /**
     * LOW Price Alert.
     */
    public static final int MID_LOWPRICE = 101;

    /**
     * HIGH Price Alert.
     */
    public static final int MID_HIGHPRICE = 102;


    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQI_BID = 103;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQI_OFFER = 104;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQI_2_SIDES = 105;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQT_BID = 106;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQT_OFFER = 107;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQT_2_SIDES = 108;
    /**
     * RFQ Change message ID.
     */
    public static final int MID_RFQT_CROSS = 109;

    /**
     * Strategy creation strategy created event id.
     */
    public static final int MID_STRATEGY_CREATION_CREATED = 200;

    /**
     * Event ID to update order history
     */
    public static final int MID_UPDATE_ORDERHISTORY = 1000;


}
