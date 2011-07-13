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
import patsystems.api.delphi.Order;

import javax.swing.table.AbstractTableModel;
import javax.swing.SwingUtilities;
import java.util.LinkedList;

/**
 * <h1>OrderBookTableModel</h1>
 * <p>As the order book follows the Model View Controller structure the order book model
 * will be used to automatically update the book table when orders are altered in the order
 * hashmap</p>
 * <p>Copyright <B>Patsystems UK Limited 2000-2007</b></p>
 *
 * @author ACloud (coder)
 * @author JSharpe (documents and reviewer)
 * @version "%I%, %G%"
 * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
 * @since (Java Demo App) version 1.0
 */
public class OrderBookTableModel extends AbstractTableModel
{

    /**
     * <p>This is the copyright notice for this class </p>
     *
     * @copyright<br><p><B>Patsystems UK Limited 2000-2007</b></p>
     */
    public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    private LinkedList<Order> ordersList;
    protected ClientAPI clientAPI;

    public final String[] columnNames = {"OrderID",
                                        "Status",
                                        "Exchange",
                                        "Commodity",
                                        "Contract",
                                        "Side",
                                        "Price",
                                        "Target Lots",
                                        "Actual Lots",
                                        "Filled Lots",
                                        "Order Type",
                                        "Trader"};

    public static final int orderID = 0;
    public static final int status = 1;
    public static final int exchange = 2;
    public static final int commodity = 3;
    public static final int contract = 4;
    public static final int side = 5;
    public static final int price = 6;
    public static final int targetLots = 7;
    public static final int actualLots = 8;
    public static final int filledLots = 9;
    public static final int orderType = 10;
    public static final int trader = 11;

    /**
     * Constructor
     *
     * @param clientAPI
     */
    public OrderBookTableModel(ClientAPI clientAPI)
    {
        this.clientAPI = clientAPI;
        ordersList = new LinkedList<Order>();
    }

    /**
     * returns the number of rows
     */
    public int getRowCount()
    {
        return ordersList.size();
    }

    /**
     * returns the number of columns
     */
    public int getColumnCount()
    {
        return columnNames.length;
    }

    /**
     * returns the object at the specified row, column location
     *
     * @param rowIndex
     * @param columnIndex
     */
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Order order = ordersList.get(rowIndex);
        switch (columnIndex)
        {
        case orderID:
            return order.orderID;
        case status:
            return order.status;
        case exchange:
            return order.exchange;
        case commodity:
            return order.commodity;
        case contract:
            return order.contract;
        case side:
            return order.side;
        case price:
            return order.price;
        case targetLots:
            return order.targetLots;
        case actualLots:
            return order.actualLots;
        case filledLots:
            return order.filledLots;
        case orderType:
            return order.orderType;
        case trader:
            return order.trader;
        }
        return null;
    }

    /**
     * returns the column name
     *
     * @param columnIndex
     */
    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

    /**
     * creates a thread for updating the table with all the order information
     *
     * @param order
     */
    public void addOrder(Order order)
    {
        ordersList.add(order);
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                fireTableRowsInserted(ordersList.size() - 1, ordersList.size());
            }
        });
    }

    /**
     * updates an order within the table
     *
     * @param order
     */
    public void updateOrder(Order order)
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                fireTableRowsUpdated(ordersList.size() - 1, ordersList.size());
            }
        });
    }


}
