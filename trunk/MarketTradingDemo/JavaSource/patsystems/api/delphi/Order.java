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
 * <h1>Order</h1>
 * <p>This class will take an order details struct from the Delphi API (via the JavaWrapper) and
 * store all the order's details in it's own properties</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class Order
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    public int index;
    public String orderID;
    public String exchOrderID;
    public String trader;
    public String orderType;
    public String exchange;
    public String commodity;
    public String contract;
    public String side;
    public String price;
    public int targetLots;
    public int filledLots;
    public byte status;
    public int actualLots;
    public int orderHistorySeq;

    /**
     *  constructor - takes in the order buffer
     * @param buffer
     */
    public Order(byte[] buffer)
    {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        index = fetcher.getInt();
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        orderID = fetcher.getString(StructDefinitions.SIZE_ORDER_ID);
        fetcher.skipString(StructDefinitions.SIZE_ORDER_ID);
        exchOrderID = fetcher.getString(StructDefinitions.SIZE_EXCH_ORDER_ID);
        fetcher.skipString(StructDefinitions.SIZE_USER_NAME);
        trader = fetcher.getString(StructDefinitions.SIZE_ACCOUNT_NAME);
        orderType = fetcher.getString(StructDefinitions.SIZE_ORDER_TYPE);
        exchange = fetcher.getString(StructDefinitions.SIZE_EXCHANGE_NAME);
        commodity = fetcher.getString(StructDefinitions.SIZE_COMMODITY_NAME);
        contract = fetcher.getString(StructDefinitions.SIZE_CONTRACT_NAME);
        if (fetcher.getChar() == 'B')
            side = "BUY";
        else
            side = "SELL";
        price = fetcher.getString(StructDefinitions.SIZE_PRICE);
        fetcher.skipString(StructDefinitions.SIZE_PRICE);
        targetLots = fetcher.getInt();
        fetcher.skipString(StructDefinitions.SIZE_ORDER_ID);
        filledLots = fetcher.getInt();
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_PRICE);
        status = fetcher.getByte();
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_TIME);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_TIME);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_TIME);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_TIME);
        fetcher.skipString(StructDefinitions.SIZE_NON_EXEC_REASON);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_REFERENCE);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_DATE);
        fetcher.skipString(StructDefinitions.SIZE_TIME);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_BATCH_ID);
        fetcher.skipString(StructDefinitions.SIZE_BATCH_TYPE);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_BATCH_STATUS);
        fetcher.skipString(StructDefinitions.SIZE_PARENT_ID);
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        fetcher.skipString(StructDefinitions.SIZE_BIG_REFERENCE);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skipString(StructDefinitions.SIZE_QUOTE_ID);
        fetcher.skip(StructDefinitions.SIZE_INT);
        fetcher.skip(StructDefinitions.SIZE_INT);
        actualLots = fetcher.getInt();
        fetcher.skip(StructDefinitions.SIZE_SENDER_LOCATION_ID);
        fetcher.skip(StructDefinitions.SIZE_RAW_PRICE);
        fetcher.skip(StructDefinitions.SIZE_RAW_PRICE2);
        fetcher.skip(StructDefinitions.SIZE_EXECUTION_ID);
        fetcher.skip(StructDefinitions.SIZE_CLIENT_ORDER_ID);
        fetcher.skip(StructDefinitions.SIZE_ESA_REFERENCE);
        fetcher.skip(StructDefinitions.SIZE_ISIN_CODE);
        fetcher.skip(StructDefinitions.SIZE_CASH_PRICE);
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        fetcher.skip(StructDefinitions.SIZE_BASIS_REF);
        fetcher.skip(StructDefinitions.SIZE_DATE);
        fetcher.skip(StructDefinitions.SIZE_TIME);
        fetcher.skip(StructDefinitions.SIZE_CHAR);
        fetcher.skip(StructDefinitions.SIZE_APIM_USER);
        fetcher.skip(StructDefinitions.SIZE_ICS_NEARLEG_PRICE);
        fetcher.skip(StructDefinitions.SIZE_ICS_FARLEG_PRICE);
        fetcher.skip(StructDefinitions.SIZE_DATE);
        orderHistorySeq = fetcher.getInt();

    }
}
