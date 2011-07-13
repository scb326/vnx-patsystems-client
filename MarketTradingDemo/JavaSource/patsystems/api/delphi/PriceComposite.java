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
 * <h1>PriceComposite</h1>
 * <p>This class will receive a buffer from the price update callback and then break it down
 * into all the different prices that make up a composite price struct from the delphi api.
 * These prices include, bid, offer, rfq, array of last 20 bid, offer and last traded prices
 * which can be used to create a depth of market</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class PriceComposite
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    public PriceDetail bid;
    public PriceDetail offer;
    public PriceDetail impliedBid;
    public PriceDetail impliedOffer;
    public PriceDetail rfq;
    public PriceDetail[] last;
    public PriceDetail total;
    public PriceDetail high;
    public PriceDetail low;
    public PriceDetail opening;
    public PriceDetail closing;
    public PriceDetail[] bidDOM;
    public PriceDetail[] offerDOM;
    public PriceDetail limitUp;
    public PriceDetail limitDown;
    public PriceDetail referencePrice;
    public PriceDetail currentSettlement;
    public PriceDetail startOfDaySettlement;
    public PriceDetail newSettlement;
    public PriceDetail indBid;
    public PriceDetail indOffer;
    public int status;
    public int changeMask;

    protected ClientAPI clientAPI;

    /**
     * constructor - takes in the price buffer and the client API reference
     * @param priceBuffer
     * @param clientAPI
     */
    public PriceComposite(byte[] priceBuffer, ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;
        ByteArrayFetcher fetcher = new ByteArrayFetcher(priceBuffer);

        bid = new PriceDetail(fetcher);
        offer = new PriceDetail(fetcher);
        impliedBid = new PriceDetail(fetcher);
        impliedOffer = new PriceDetail(fetcher);
        rfq = new PriceDetail(fetcher);

        last = new PriceDetail[20];
        for (int x = 0; x < 20; x++)
            last[x] = new PriceDetail(fetcher);

        total = new PriceDetail(fetcher);
        high = new PriceDetail(fetcher);
        low = new PriceDetail(fetcher);
        opening = new PriceDetail(fetcher);
        closing = new PriceDetail(fetcher);

        bidDOM = new PriceDetail[20];
        for (int x = 0; x < 20; x++)
            bidDOM[x] = new PriceDetail(fetcher);

        offerDOM = new PriceDetail[20];
        for (int x = 0; x < 20; x++)
            offerDOM[x] = new PriceDetail(fetcher);

        limitUp = new PriceDetail(fetcher);
        limitDown = new PriceDetail(fetcher);
        referencePrice = new PriceDetail(fetcher);
        currentSettlement = new PriceDetail(fetcher);
        startOfDaySettlement = new PriceDetail(fetcher);
        newSettlement = new PriceDetail(fetcher);
        indBid = new PriceDetail(fetcher);
        indOffer = new PriceDetail(fetcher);
        status = fetcher.getInt();
        changeMask = fetcher.getInt();
    }

}
