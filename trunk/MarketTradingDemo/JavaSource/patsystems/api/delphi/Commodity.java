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
 * <h1>Commodity</h1>
 * <p>Stores the data for a commodity received directly from the Delphi API (via the JavaWrapper)</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class Commodity
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    public Exchange exchange;
    public String name;
    public String currency;
    public String group;
    public String onePoint;
    public int ticksPerPoint;
    public String tickSize;

    private ClientAPI clientAPI;

    /**
     * constructor
     *
     * @param buffer
     * @param clientAPI
     */
    public Commodity(byte[] buffer, ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;
        parseBuffer(buffer);
    }
     /**
     * parses the buffer
     * @param buffer
     */
    protected void parseBuffer(byte[] buffer)
    {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        String exchangeName = fetcher.getString(StructDefinitions.SIZE_EXCHANGE_NAME);

        exchange = clientAPI.exchangeMap.get(exchangeName);
        name = fetcher.getString(StructDefinitions.SIZE_COMMODITY_NAME);
        currency = fetcher.getString(StructDefinitions.SIZE_CURRENCY);
        group = fetcher.getString(StructDefinitions.SIZE_GROUP);
        onePoint = fetcher.getString(StructDefinitions.SIZE_ONE_POINT);
        ticksPerPoint = fetcher.getInt();
        tickSize = fetcher.getString(StructDefinitions.SIZE_TICK_SIZE);
    }

    /**
     * gets the key (unique)
     *
     * @return exchange.name + "/" + name
     */
    public String getKey()
    {
        return exchange.name + "/" + name;
    }
}
