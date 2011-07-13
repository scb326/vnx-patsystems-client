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
 * <h1>StructDefinitions</h1>
 * <p>This is the structure of the messages going to and from the Delphi API (via the JavaWrapper)
 * and the Java Demo Client. </p>
 * <p>Includes the size (i.e. how much space) each value occupies in the buffer</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public interface StructDefinitions
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    public static final int SIZE_INT = 4;
    public static final int SIZE_EXCHANGE_NAME = 10;
    public static final int SIZE_CHAR = 1;
    public static final int SIZE_ACCOUNT_NAME = 20;
    public static final int SIZE_BACK_OFFICE_ID = 20;
    public static final int SIZE_COMMODITY_NAME = 10;
    public static final int SIZE_CONTRACT_NAME = 50;
    public static final int SIZE_ORDER_TYPE_NAME = 10;
    public static final int SIZE_CURRENCY = 10;
    public static final int SIZE_GROUP = 10;
    public static final int SIZE_DECIMAL = 10;
    public static final int SIZE_ONE_POINT = 10;
    public static final int SIZE_TICK_SIZE = 10;
    public static final int SIZE_EXPIRY_DATE = 8;
    public static final int SIZE_LAST_TRADE_DATE = 8;
    public static final int SIZE_EXTERNAL = 10;
    public static final int SIZE_ORDER_TYPE = 10;
    public static final int SIZE_TICKET_TYPE = 2;
    public static final int SIZE_TICKET_VERSION = 3;
    public static final int SIZE_REPORT_TYPE = 20;
    public static final int SIZE_PRICE = 20;
    public static final int SIZE_ORDER_ID = 10;
    public static final int SIZE_DATE = 8;
    public static final int SIZE_REFERENCE = 25;
    public static final int SIZE_ESA_REFERENCE = 50;
    public static final int SIZE_TIME = 6;
    public static final int SIZE_BATCH_ID = 10;
    public static final int SIZE_BATCH_TYPE = 10;
    public static final int SIZE_BATCH_STATUS = 10;
    public static final int SIZE_PARENT_ID = 10;
    public static final int SIZE_BIG_REFERENCE = 255;
    public static final int SIZE_SENDER_LOCATION_ID = 32;
    public static final int SIZE_RAW_PRICE = 20;
    public static final int SIZE_RAW_PRICE2 = 20;
    public static final int SIZE_EXECUTION_ID = 70;
    public static final int SIZE_CLIENT_ORDER_ID = 20;
    public static final int SIZE_APIM_USER = 20;
    public static final int SIZE_YDSP_AUDIT = 10;
    public static final int SIZE_ICS_NEARLEG_PRICE = 10;
    public static final int SIZE_ICS_FARLEG_PRICE = 10;
    public static final int SIZE_TICK = 10;
    public static final int SIZE_EXCHANGE_FIELDS = 10;
    public static final int SIZE_BACK_OFFICE_ID_TEXT = 20;
    public static final int SIZE_BADGE_NUMBER_TEXT = 5;
    public static final int SIZE_EXCH_ORDER_ID = 30;
    public static final int SIZE_USER_NAME = 10;
    public static final int SIZE_NON_EXEC_REASON = 60;
    public static final int SIZE_QUOTE_ID = 120;
    public static final int SIZE_ISIN_CODE = 20;
    public static final int SIZE_CASH_PRICE = 20;
    public static final int SIZE_BASIS_REF = 20;
    public static final int SIZE_LOCAL_USER_NAME = 10;
    public static final int SIZE_LOCAL_TRADER = 20;
    public static final int SIZE_LOCAL_BOF = 20;
    public static final int SIZE_LOCAL_ORDER_ID = 10;
    public static final int SIZE_EXCHANGE_ACCOUNT = 10;
    public static final int SIZE_ROUTINGID1 = 10;
    public static final int SIZE_ROUTINGID2 = 10;


    /**
     * the new order structure
     */
    public static final int SIZE_NEW_ORDER_STRUCT =
            (SIZE_ACCOUNT_NAME + 1) +
                    (SIZE_ORDER_TYPE_NAME + 1) +
                    (SIZE_EXCHANGE_NAME + 1) +
                    (SIZE_CONTRACT_NAME + 1) +
                    (SIZE_CONTRACT_NAME + 1) +
                    1                           /* Buy Or Sell */ +
                    (SIZE_PRICE + 1)            /* Price */ +
                    (SIZE_PRICE + 1)            /* Price 2 */ +
                    SIZE_INT                    /* Lots */ +
                    (SIZE_ORDER_ID + 1)         /* Linked Order */ +
                    1                           /* (O)pen Or (C)lose */ +
                    SIZE_INT                    /* XRef */ +
                    SIZE_INT                    /* XRefP */ +
                    (SIZE_DATE + 1)             /* GTD Date */ +
                    SIZE_CHAR                   /* Trigger Now */ +
                    (SIZE_REFERENCE + 1)        /* Reference field */ +
                    (SIZE_ESA_REFERENCE + 1)    /* ESA Referenced field */ +
                    SIZE_INT                    /* Priority */ +
                    (SIZE_DATE + 1)             /* Trigger date */ +
                    (SIZE_TIME + 1)             /* Trigger time */ +
                    (SIZE_BATCH_ID + 1)         /* Batch id */ +
                    (SIZE_BATCH_TYPE + 1)       /* Batch type */ +
                    (SIZE_INT)                  /* Batch count */ +
                    (SIZE_BATCH_STATUS + 1)     /* Batch status */ +
                    (SIZE_PARENT_ID + 1) +      /* OMI - parent id */
                    SIZE_CHAR                   /* OMI - Done for day */ +
                    (SIZE_BIG_REFERENCE + 1)    /* Big Reference field. CR291*/ +
                    (SIZE_SENDER_LOCATION_ID + 1) +
                    (SIZE_RAW_PRICE + 1) +
                    (SIZE_RAW_PRICE2 + 1) +
                    (SIZE_EXECUTION_ID + 1) +
                    (SIZE_CLIENT_ORDER_ID + 1) +
                    (SIZE_CHAR) +                   /*apim code*/
                    (SIZE_APIM_USER + 1) +          /*apim user*/
                    (SIZE_YDSP_AUDIT + 1) +
                    (SIZE_ICS_NEARLEG_PRICE + 1) +  /*ICS Near Leg Price*/
                    (SIZE_ICS_FARLEG_PRICE + 1)+    /*ICS Far Leg Price*/
                    + SIZE_INT                      /*Iceberg MaxClipSize*/
                    + SIZE_INT                      /*Iceberg MinClipSize*/
                    + SIZE_CHAR                     /*Iceberg Randomize*/
                    + SIZE_TICKET_TYPE + 1          /*'01'=normal, '02'=iceberg, '03'=ghost*/
                    + SIZE_TICKET_VERSION + 1       /*version, currently '001'*/
                    + SIZE_EXCHANGE_FIELDS + 1      /* Exchange fields*/
                    + SIZE_BACK_OFFICE_ID_TEXT + 1  /*Back office ID*/
                    + SIZE_BADGE_NUMBER_TEXT + 1    /*Badge number*/
                    + SIZE_LOCAL_USER_NAME + 1
                    + SIZE_LOCAL_TRADER + 1
                    + SIZE_LOCAL_BOF + 1
                    + SIZE_LOCAL_ORDER_ID + 1
                    + SIZE_EXCHANGE_ACCOUNT + 1
                    + SIZE_ROUTINGID1 + 1
                    + SIZE_ROUTINGID2 +1
            ;

    /**
     * the order detail structure
     */
    public static final int SIZE_ORDER_DETAIL_STRUCT =
            SIZE_INT /* Index */ +
                    1 /* Historic */ +
                    1 /* Checked */ +
                    (SIZE_ORDER_ID + 1) /* Order ID */ +
                    (SIZE_ORDER_ID + 1) /* Display ID */ +
                    (SIZE_EXCH_ORDER_ID + 1) +
                    (SIZE_USER_NAME + 1) +
                    (SIZE_ACCOUNT_NAME + 1) +
                    (SIZE_ORDER_TYPE_NAME + 1) +
                    (SIZE_EXCHANGE_NAME + 1) +
                    (SIZE_COMMODITY_NAME + 1) +
                    (SIZE_CONTRACT_NAME + 1) +
                    1 /* Buy Or Sell */ +
                    (SIZE_PRICE + 1) /* Price */ +
                    (SIZE_PRICE + 1) /* Price 2 */ +
                    SIZE_INT /* Lots */ +
                    (SIZE_ORDER_ID + 1) /* Linked Order */ +
                    SIZE_INT /* Amount Filled */ +
                    SIZE_INT /* Number of Fills */ +
                    (SIZE_PRICE + 1) /* Average Price */ +
                    1 /* Status */ +
                    1 /* (O)pen Or (C)lose */ +
                    (SIZE_DATE + 1) /* Date Sent */ +
                    (SIZE_TIME + 1) /* Time Sent */ +
                    (SIZE_DATE + 1) /* Date Exchange Recd */ +
                    (SIZE_TIME + 1) /* Time Exchange Recd */ +
                    (SIZE_DATE + 1) /* Date Host Recd */ +
                    (SIZE_TIME + 1) /* Time Host Recd */ +
                    (SIZE_DATE + 1) /* Date Exchange Acknowledged */ +
                    (SIZE_TIME + 1) /* Time Exchange Acknowledged */ +
                    (SIZE_NON_EXEC_REASON + 1) +
                    SIZE_INT /* XRef */ +
                    SIZE_INT /* XRefP */ +
                    SIZE_INT /* Update Sequence */ +
                    (SIZE_DATE + 1) /* Good till date */ +
                    (SIZE_REFERENCE + 1) /* Reference field */ +
                    SIZE_INT /* Priority */ +
                    (SIZE_DATE + 1) /* Trigger date */ +
                    (SIZE_TIME + 1) /* Trigger time */ +
                    SIZE_INT + /* Substate */
                    (SIZE_BATCH_ID + 1) /* Batch id */ +
                    (SIZE_BATCH_TYPE + 1) /* Batch type */ +
                    (SIZE_INT) /* Batch count */ +
                    (SIZE_BATCH_STATUS + 1) /* Batch status */ +
                    (SIZE_PARENT_ID + 1) +
                    SIZE_CHAR /* Done for day */ +
                    (SIZE_BIG_REFERENCE + 1) /* Big Reference field. */ +
                    (SIZE_INT) /* Timeout value */ +
                    (SIZE_QUOTE_ID + 1) /* Quote id. */ +
                    SIZE_INT + /* Cumulative Volume (known as Lots posted in delphi api.)*/
                    SIZE_INT + /* Child Count */
                    SIZE_INT + /* Actual Lots */
                    (SIZE_SENDER_LOCATION_ID + 1) +
                    (SIZE_RAW_PRICE + 1) +
                    (SIZE_RAW_PRICE2 + 1) +
                    (SIZE_EXECUTION_ID + 1) +
                    (SIZE_CLIENT_ORDER_ID + 1) +
                    (SIZE_ESA_REFERENCE + 1) +
                    (SIZE_ISIN_CODE + 1) +
                    (SIZE_CASH_PRICE + 1) +
                    SIZE_CHAR + /* Methodology */
                    (SIZE_BASIS_REF + 1) +
                    (SIZE_DATE + 1) /* Stas order entry date */ +
                    (SIZE_TIME + 1) + /* Stas order entry time */
                    (SIZE_CHAR) + //apim code
                    (SIZE_APIM_USER + 1) + //apim user
                    (SIZE_ICS_NEARLEG_PRICE + 1) + // ICS near leg price
                    (SIZE_ICS_NEARLEG_PRICE + 1) + // ICS far leg price
                    (SIZE_DATE + 1) + //Creation date
                    (SIZE_INT) //Order History Sequence Number.
                    + SIZE_INT    // min clip size
                    + SIZE_INT    // max clip size
                    + 1           // randomize (='Y' or 'N')
                    + SIZE_INT    // profit level
                    + SIZE_INT    // order sequence no
                    + SIZE_EXCHANGE_FIELDS + 1 // Exchange fields
                    + SIZE_BACK_OFFICE_ID_TEXT + 1 // Back office ID
                    + SIZE_BADGE_NUMBER_TEXT + 1       // Badge number
                    +SIZE_INT +     //GT status
                    + SIZE_LOCAL_USER_NAME + 1
                    + SIZE_LOCAL_TRADER + 1
                    + SIZE_LOCAL_BOF + 1
                    + SIZE_LOCAL_ORDER_ID + 1
                    + SIZE_EXCHANGE_ACCOUNT + 1
                    + SIZE_ROUTINGID1 + 1
                    + SIZE_ROUTINGID2 +1
            ;
    /**
     * the amend order structure
     */
    public static final int SIZE_AMEND_ORDER_STRUCT =
            (SIZE_PRICE + 1) /* Price */ +
                    (SIZE_PRICE + 1) /* Price 2 */ +
                    SIZE_INT /* Lots */ +
                    (SIZE_ORDER_ID + 1) /* Linked Order */ +
                    1 /* (O)pen Or (C)lose */ +
                    (SIZE_ACCOUNT_NAME + 1) +
                    (SIZE_REFERENCE + 1) /* Reference field */ +
                    SIZE_INT /* Priority */ +
                    (SIZE_DATE + 1) /* Trigger date */ +
                    (SIZE_TIME + 1) /* Trigger time */ +
                    (SIZE_BATCH_ID + 1) /* Batch id */ +
                    (SIZE_BATCH_TYPE + 1) /* Batch type */ +
                    (SIZE_INT) /* Batch count */ +
                    (SIZE_BATCH_STATUS + 1) /* Batch status */ +
                    (SIZE_PARENT_ID + 1) +
                    SIZE_CHAR /* Done for day */ +
                    (SIZE_BIG_REFERENCE + 1) /* Big Reference field. CR291*/ +
                    (SIZE_SENDER_LOCATION_ID + 1) +
                    (SIZE_RAW_PRICE + 1) +
                    (SIZE_RAW_PRICE2 + 1) +
                    (SIZE_EXECUTION_ID + 1) +
                    (SIZE_CLIENT_ORDER_ID + 1) +
                    (SIZE_ESA_REFERENCE + 1) +
                    (SIZE_YDSP_AUDIT + 1) +
                    (SIZE_ICS_NEARLEG_PRICE + 1) +// ICS Near Leg Price
                    (SIZE_ICS_FARLEG_PRICE + 1) // ICS Far Leg Price
                    + SIZE_INT    // clip size
                    + SIZE_LOCAL_USER_NAME + 1
                    + SIZE_LOCAL_TRADER + 1
                    + SIZE_LOCAL_BOF + 1
                    + SIZE_LOCAL_ORDER_ID + 1
                    + SIZE_EXCHANGE_ACCOUNT + 1
                    + SIZE_ROUTINGID1 + 1
                    + SIZE_ROUTINGID2 +1
            ;


}
