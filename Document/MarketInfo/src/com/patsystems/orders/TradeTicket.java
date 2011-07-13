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

import patsystems.api.delphi.ClientAPI;
import patsystems.api.delphi.Commodity;
import patsystems.api.delphi.Contract;
import patsystems.api.delphi.OrderType;


import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Enumeration;

/**
 * <h1>TradeTicket</h1>
 * <p>Allows the user to place a trade</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class TradeTicket extends JFrame implements ActionListener
{
    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    protected JButton buttonTrade;
    protected JButton buttonCancel;
    protected JComboBox comboExchanges;
    protected JComboBox comboCommodities;
    protected JComboBox comboContracts;
    protected JComboBox comboAccounts;
    protected JComboBox comboOrderTypes;
    protected JComboBox comboSide;
    protected JComboBox comboOpenClose;
    protected JTextField textPrice;
    protected JTextField textLots;

    protected ClientAPI clientAPI;

    /**
     * Constructor
     *
     * @param clientAPI
     */
    public TradeTicket(ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;
    }

    /**
     * Initialises the trade ticket
     */
    public void init()
    {
        setTitle("Trade Ticket");

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(10, 2));

        JLabel labelTraderAccount = new JLabel("  Trader Account");
        JLabel labelExchanges = new JLabel("  Exchange");
        JLabel labelCommodities = new JLabel("  Commodity");
        JLabel labelContract = new JLabel("  Contract");
        JLabel labelOrderTypes = new JLabel("  Order Type");
        JLabel labelSide = new JLabel("  Side");
        JLabel labelOpenClose = new JLabel("  Open or Close");
        JLabel labelPrice = new JLabel("  Price");
        JLabel labelLots = new JLabel("  Lots");
        comboExchanges = new JComboBox();
        comboExchanges.addActionListener(this);
        comboCommodities = new JComboBox();
        comboCommodities.addActionListener(this);
        comboContracts = new JComboBox();
        comboContracts.addActionListener(this);
        comboAccounts = new JComboBox();
        comboOrderTypes = new JComboBox();
        comboSide = new JComboBox();
        comboOpenClose = new JComboBox();
        textPrice = new JTextField("0.0");
        textLots = new JTextField("0");

        panelTop.add(labelTraderAccount);
        panelTop.add(comboAccounts);
        panelTop.add(labelExchanges);
        panelTop.add(comboExchanges);
        panelTop.add(labelCommodities);
        panelTop.add(comboCommodities);
        panelTop.add(labelContract);
        panelTop.add(comboContracts);
        panelTop.add(labelOrderTypes);
        panelTop.add(comboOrderTypes);
        panelTop.add(labelOpenClose);
        panelTop.add(comboOpenClose);
        panelTop.add(labelSide);
        panelTop.add(comboSide);
        panelTop.add(labelPrice);
        panelTop.add(textPrice);
        panelTop.add(labelLots);
        panelTop.add(textLots);


        populateAccountsComboBox();
        populateSideCombo();
        populateOpenCloseCombo();
        populateExchangeComboxBox();

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1, 2));

        buttonTrade = new JButton("Trade");
        buttonTrade.addActionListener(this);
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(this);

        panelBottom.add(buttonTrade);
        panelBottom.add(buttonCancel);

        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * populates the <code>open/close</code> combo box
     */
    private void populateOpenCloseCombo()
    {
        comboOpenClose.addItem("OPEN");
        comboOpenClose.addItem("CLOSE");

    }

    /**
     * populates the <code>side</code> combo box
     */
    private void populateSideCombo()
    {
        comboSide.addItem("BUY");
        comboSide.addItem("SELL");
    }

    /**
     * populates the <code>trader account</code> combo box
     */
    private void populateAccountsComboBox()
    {
        Enumeration<String> keys = clientAPI.accountMap.keys();
        while (keys.hasMoreElements())
            comboAccounts.addItem(keys.nextElement());
    }

    /**
     * populates the <code>order type</code> combo box
     */
    private void populateOrderTypeComboBox()
    {
        comboOrderTypes.removeAllItems();
        Enumeration<String> keys = clientAPI.orderTypeMap.keys();
        while (keys.hasMoreElements())
        {
            String key = keys.nextElement();
            OrderType orderType = clientAPI.orderTypeMap.get(key);
            if (orderType.exchange.name == comboExchanges.getSelectedItem())
                comboOrderTypes.addItem(orderType.name);
        }
    }

    /**
     * popuates the <code> exchange</code> combo box
     */
    private void populateExchangeComboxBox()
    {
        Enumeration<String> keys = clientAPI.exchangeMap.keys();
        while (keys.hasMoreElements())
            comboExchanges.addItem(keys.nextElement());
        populateCommodityComboxBox((String) comboExchanges.getSelectedItem());
        populateOrderTypeComboBox();
    }

    /**
     * populates the <code>commodity</code> combo box
     *
     * @param exchange
     */
    private void populateCommodityComboxBox(String exchange)
    {
        comboCommodities.removeAllItems();
        if (exchange != "")
        {
            Enumeration<String> keys = clientAPI.commodityMap.keys();
            while (keys.hasMoreElements())
            {
                Commodity commodity = clientAPI.commodityMap.get(keys.nextElement());
                if (commodity.exchange.name == exchange)
                    comboCommodities.addItem(commodity.name);
            }
        }

        populateContractComboxBox((String) comboExchanges.getSelectedItem(), (String) comboCommodities.getSelectedItem());

    }

    /**
     * populates the <code>contract</code> combo box
     *
     * @param exchange
     * @param commodity
     */
    private void populateContractComboxBox(String exchange, String commodity)
    {
        comboContracts.removeAllItems();
        if (exchange != "" && commodity != "")
        {
            Enumeration<String> keys = clientAPI.contractMap.keys();
            while (keys.hasMoreElements())
            {
                Contract contract = clientAPI.contractMap.get(keys.nextElement());
                if (contract.exchange.name == exchange && contract.commodity.name == commodity)
                    comboContracts.addItem(contract.name);
            }
        }
    }

    /**
     * creates a byte buffer and populates it with the relevant order information and
     * passes it to the JavaWrapper
     *
     * @throws Exception
     */
    private void sendTradeRequest() throws Exception
    {
        //WE NEED TO CHECK THAT THE LOTS VALUE IS AN INTEGER
        try
        {

            int lots = Integer.parseInt(textLots.getText());
            String account = (String) comboAccounts.getSelectedItem();
            String exchange = (String) comboExchanges.getSelectedItem();
            String commodity = (String) comboCommodities.getSelectedItem();
            String contract = (String) comboContracts.getSelectedItem();
            String orderType = (String) comboOrderTypes.getSelectedItem();
            String openClose = (String) comboOpenClose.getSelectedItem();
            String side = (String) comboSide.getSelectedItem();
            String price = textPrice.getText();

            TradeRequest tradeRequest = new TradeRequest(account, exchange, commodity, contract, orderType, openClose, side, price, lots);

            clientAPI.doAddOrder(tradeRequest.buffer);


        }
        catch (NumberFormatException nx)
        {
            JOptionPane.showMessageDialog(this, "Enter a valid integer for the number of lots");
        }

    }


    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == comboExchanges)
        {
            populateCommodityComboxBox((String) comboExchanges.getSelectedItem());
            populateOrderTypeComboBox();
        }
        else
            if (e.getSource() == comboCommodities)
                populateContractComboxBox((String) comboExchanges.getSelectedItem(), (String) comboCommodities.getSelectedItem());
            else
                if (e.getSource() == buttonTrade)
                {
                    try
                    {
                        sendTradeRequest();
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        clientAPI.logFile.addMessage(e1.getMessage());
                    }

                }
                else
                    if (e.getSource() == buttonCancel)
                        dispose();
    }

}
