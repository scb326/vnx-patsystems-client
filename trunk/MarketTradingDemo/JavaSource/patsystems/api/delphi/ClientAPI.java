package patsystems.api.delphi;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import com.patsystems.*;
import com.patsystems.orders.OrdersHashMap;
import com.vnx.patsystems.util.Feeder;

public class ClientAPI {
	
    private final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";
    private final String wrapperFileName = "JavaWrapper.dll";    
    private final String workingDir = "E:\\Workspace\\J2EE Workspace\\MarketTradingDemo\\" +
	"\\lib\\";
    
    private final String hostServer = "203.116.11.35:9412";
    private final String priceServer = "203.116.11.33:9410";
    private final String username = "VNEPATSAPI";
    private final String password = "QQWWEE789";
    private final String applicationId = "TPE QUOTES";
    private final String license = "DBA670U001";

    public CallBackProcessor callbackThread;
    protected static byte[] msgBuff = new byte[8192];
    public Logging logFile;
    public ConcurrentHashMap<String, TraderAccount> accountMap;
    public ConcurrentHashMap<String, Exchange> exchangeMap;
    public ConcurrentHashMap<String, Commodity> commodityMap;
    public ConcurrentHashMap<String, Contract> contractMap;
    public ConcurrentHashMap<String, String> reportMap;
    public ConcurrentHashMap<String, OrderType> orderTypeMap;
    public ConcurrentHashMap<String, Feeder> subscriberMap;
    public OrdersHashMap<String, Order> orderMap;

    public boolean isLogOffCalled;
    public Feeder feeder;

	protected native int initialise(
		String runtimeDir, String logDir, boolean demo, boolean test, boolean connectivity, 
		String ApplicationID, boolean InitReset, String ApplicationVer, String LicenseID
	);
	
	protected native int logon(
		String user, String pwd, String newPwd, boolean force, boolean reports
	);
	
	protected native int setDebugFlags(int Logging);
	protected native int openConnections(String[] hosts, String[] prices);
	protected native void processCallbacks(byte[] buffer);
	protected native String getErrorMessage(int error);
	protected native int logoff();
	protected native int countAccounts(int[] count);
	protected native int countExchanges(int[] count);
	protected native int countCommodities(int[] count);
	protected native int countContracts(int[] result);
	protected native int countOrderTypes(int[] count);
	protected native int countReportTypes(int[] count);
	protected native int getExchange(int index, byte[] buffer);
	protected native int getAccount(int index, byte[] buffer);
	protected native int getCommodity(int index, byte[] buffer);
	protected native int getContract(int index, byte[] buffer);
	protected native int getOrderType(int index, byte[] buffer);
	protected native int getReportType(int index, byte[] buffer);
	protected native int getReportSize(String report, int[] size);
	protected synchronized native int getReport(String report, byte[] buffer);
	protected native int subscribe(String exchange, String commodity, String contract);
	protected native int unsubscribe(String exchange, String commodity, String contract);
	protected native int getContractPrices(
		String exchange, String commodity, String contract, byte[] buffer
	);
	protected native int addOrder(byte[] buffer);
	protected native int getOrderById(String orderID, byte[] buffer);
	protected native int cancelOrder(String OrderID);
	protected native int amendOrder(String orderID, byte[] buffer);
	
	protected native int setHostHandShakePeriods(int interval,int  timeout);
	protected native int setPriceHandShakePeriods(int interval, int timeout);
    
    public ClientAPI(Feeder feeder) {
        try {
        	this.feeder = feeder;
            logFile = new Logging(workingDir);
            initialiseClient();
            isLogOffCalled = false;
            System.out.println("Initializing the API");
        }
        catch (Throwable e) {
            e.printStackTrace();
            logFile.addMessage(e.getMessage());
        }
    }

    protected void initialiseClient() throws Exception {
        exchangeMap = new ConcurrentHashMap<String, Exchange>();
        accountMap = new ConcurrentHashMap<String, TraderAccount>();
        commodityMap = new ConcurrentHashMap<String, Commodity>();
        contractMap = new ConcurrentHashMap<String, Contract>();
        orderTypeMap = new ConcurrentHashMap<String, OrderType>();
        reportMap = new ConcurrentHashMap<String, String>();
        orderMap = new OrdersHashMap<String, Order>();
        orderMap.assignClientAPI(this);
        subscriberMap = new ConcurrentHashMap<String, Feeder>();
        
        System.load(workingDir + wrapperFileName);
        
        doInitialise();
        doSetDebugFlags(255);
        
        doSetHostHandShakePeriods(20,30);
        doSetPriceHandShakePeriods(20,30);
        
        String[] hosts = {hostServer};
        String[] prices = {priceServer};
        doOpenConnections(hosts, prices);
        Thread.sleep(3000);
        
        doLogin(username, password, "", false, true);
        
        callbackThread = new CallBackProcessor();
        callbackThread.start();
    }
    
    protected void getAPIData() throws Exception {
        byte[] buffer = new byte[8192];
        int[] count;
        
        count = doCountAccounts();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetAccount(index);
                TraderAccount account = new TraderAccount(buffer);
                accountMap.put(account.getKey(), account);
            }
        }
        
        count = doCountExchanges();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetExchange(index);
                Exchange exchange = new Exchange(buffer);
                exchangeMap.put(exchange.getKey(), exchange);
            }
        }
        
        count = doCountCommodities();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetCommodity(index);
                Commodity commodity = new Commodity(buffer, this);
                commodityMap.put(commodity.getKey(), commodity);
            }
        }

        count = doCountContracts();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetContract(index);
                Contract contract = new Contract(buffer, this);
                contractMap.put(contract.getKey(), contract);
            }
        }

        count = doCountOrderTypes();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetOrderType(index);
                OrderType orderType = new OrderType(buffer, this);
                orderTypeMap.put(orderType.getKey(), orderType);
            }
        }
        
        count = doCountReportTypes();
        if (count[0] > 0) {
            for (int index = 0; index < count[0]; index++) {
                buffer = doGetReportType(index);
                String reportType = getReportType(buffer);
                int[] reportSize = doGetReportSize(reportType);
                byte[] reportBuffer = doGetReport(reportType, reportSize[0]);
                String report = getReport(reportBuffer, reportSize[0]);
                reportMap.put(reportType, report);
            }
        }
        
        logFile.addMessage("Trader Accounts downloaded = " + accountMap.size());
        logFile.addMessage("Exchanges downloaded = " + exchangeMap.size());
        logFile.addMessage("Commodities downloaded = " + commodityMap.size());
        logFile.addMessage("Contracts downloaded = " + contractMap.size());
        logFile.addMessage("Order Types downloaded = " + orderTypeMap.size());
        logFile.addMessage("Reports downloaded = " + reportMap.size());

        feeder.countContracts();
    }

    private String getReportType(byte[] buffer) {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        return fetcher.getString(StructDefinitions.SIZE_REPORT_TYPE);
    }

    private String getReport(byte[] buffer, int reportSize) {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        return fetcher.getString(reportSize);
    }

    class CallBackProcessor implements Runnable {

        public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";
        private Thread runner = null;
        private boolean running = true;

        public void run() {
            while (running) {
                processCallbacks(msgBuff);
            }
        }

        public void start() {
            if (!isRunning()) {
                runner = new Thread(this);
                runner.start();
            }
        }

        public boolean isRunning() {
            return (runner != null && runner.isAlive());
        }

        public void stopThisCallBackThread() {
            running = false;
        }
    }

    class OrdersProcessor implements Runnable {
        
    	public static final String COPYRIGHT = "Copyright (c) Patsystems UK Limited 2000-2007";

    	public void run() {
    		
        }
    }

    public void check(int result, String msg) throws Exception {
        if (result != APIConstants.ptSuccess) {
            String errorMessage = doGetErrorMessage(result) + " in " + msg;
            JOptionPane.showMessageDialog(null, errorMessage);
            Calendar cal = new GregorianCalendar();
            int hour24 = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int ms = cal.get(Calendar.MILLISECOND);
            String time = String.valueOf(hour24) + ":" +
                    String.valueOf(min) + ":" +
                    String.valueOf(sec) + ":" +
                    String.valueOf(ms);
            logFile.addMessage(time + "\t" + errorMessage);
        }
    }

    public void doInitialise() throws Exception {
    	check(
    		initialise(
    			workingDir, workingDir, false, false, false, applicationId, 
    			true, "1.0", license
    		), 
    		"doInitialise"
    	);
    }

    public void doSetDebugFlags(int Logging) throws Exception {
        check(setDebugFlags(Logging), "doSetDebugFlags");
    }

    public void doOpenConnections(String[] hosts, String[] prices) throws Exception {
        check(openConnections(hosts, prices), "doOpenConnections");
    }

    public void doLogin(
    	String user, String password, String newPassword, boolean reset, boolean reports
    ) throws Exception {
        check(logon(user, password, newPassword, reset, reports), "doLogon");
    }

    public String doGetErrorMessage(int error) {
        String msg = getErrorMessage(error);
        return msg;
    }

    public void doLogoff() throws IOException {
    	logFile.addMessage("call doLogoff");
        
    	int status = logoff();
        if (status == 0) {
            logFile.closeFile();
            isLogOffCalled = true;            
        } else {
            JOptionPane.showMessageDialog(
            	null, "Failed To Logoff", "Logoff", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public int[] doCountAccounts() throws Exception {
        int[] accountCount = new int[1];
        check(countAccounts(accountCount), "doCountAccounts");
        return accountCount;
    }

    public int[] doCountExchanges() throws Exception {
        int[] exchangeCount = new int[1];
        check(countExchanges(exchangeCount), "doCountExchanges");
        return exchangeCount;
    }

    public int[] doCountCommodities() throws Exception {
        int[] commodityCount = new int[1];
        check(countCommodities(commodityCount), "doCountCommodities");
        return commodityCount;
    }

    public int[] doCountContracts() throws Exception {
        int[] contractCount = new int[1];
        check(countContracts(contractCount), "doCountContracts");
        return contractCount;
    }
    
    public int[] doCountOrderTypes() throws Exception {
        int[] otCount = new int[1];
        check(countOrderTypes(otCount), "doCountOrderTypes");
        return otCount;
    }

    public int[] doCountReportTypes() throws Exception {
        int[] otCount = new int[1];
        check(countReportTypes(otCount), "doCountReportTypes");
        return otCount;
    }

    public byte[] doGetAccount(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getAccount(index, buffer), "doGetAccount");
        return buffer;
    }

    public byte[] doGetExchange(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getExchange(index, buffer), "doGetExchange");
        return buffer;
    }

    public byte[] doGetCommodity(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getCommodity(index, buffer), "doGetCommodity");
        return buffer;
    }

    public byte[] doGetContract(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getContract(index, buffer), "doGetContract");
        return buffer;
    }

    public byte[] doGetOrderType(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getOrderType(index, buffer), "doGetOrderType");
        return buffer;
    }

    public byte[] doGetReportType(int index) throws Exception {
        byte[] buffer = new byte[8192];
        check(getReportType(index, buffer), "doGetReportType");
        return buffer;
    }

    public int[] doGetReportSize(String reportType) throws Exception {
        int[] size = new int[1];
        check(getReportSize(reportType, size), "doGetReportSize");
        return size;
    }

    public byte[] doGetReport(String reportType, int reportSize) throws Exception {
        byte[] report = new byte[reportSize];
        check(getReport(reportType, report), "doGetReport");
        return report;
    }

    public void doSubscribe(String exchange, String commodity, String contract) throws Exception {
        check(subscribe(exchange, commodity, contract), "doSubscribe");
    }

    public void doUnsubscribe(String exchange, String commodity, String contract) throws Exception {
        check(unsubscribe(exchange, commodity, contract), "doUnsubscribe");
    }

    public void doGetContractPrices(byte[] buffer) throws Exception {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        String exchange = fetcher.getString(StructDefinitions.SIZE_EXCHANGE_NAME);
        String commodity = fetcher.getString(StructDefinitions.SIZE_COMMODITY_NAME);
        String contract = fetcher.getString(StructDefinitions.SIZE_CONTRACT_NAME);
        byte[] priceBuffer = new byte[8192];
        
        check(
        	getContractPrices(exchange, commodity, contract, priceBuffer), "doGetPriceForContract"
        );
        
        PriceComposite prices = new PriceComposite(priceBuffer, this);
        String key = exchange + " / " + commodity + " / " + contract;

        if (subscriberMap.containsKey(key)) {
            subscriberMap.get(key).priceUpdate(prices, exchange, commodity, contract, key);
        }
    }

    public void doAddOrder(byte[] buffer) throws Exception {
        check(addOrder(buffer), "doAddOrder");
    }

    public void doGetOrderByID(byte[] buffer) throws Exception {
        ByteArrayFetcher fetcher = new ByteArrayFetcher(buffer);
        String orderID = fetcher.getString(StructDefinitions.SIZE_ORDER_ID);
        byte[] orderDetails = new byte[StructDefinitions.SIZE_ORDER_DETAIL_STRUCT];
        check(getOrderById(orderID, orderDetails), "doGetOrderByID");
        Order order = new Order(orderDetails);
        orderMap.put(order.orderID, order);
    }

    public void doCancelOrder(String orderID) throws Exception {
        check(cancelOrder(orderID), "doCancelOrder");
    }

    public void doAmendOrder(String orderID, byte[] buffer) throws Exception {
        check(amendOrder(orderID, buffer), "doAmendOrder");
    }
    
    public void doSetHostHandShakePeriods(int interval,int  timeout) throws Exception {
        check(setHostHandShakePeriods(interval, timeout), "doSetHostHandShakePeriods");
    }
    
    public void doSetPriceHandShakePeriods(int interval,int  timeout) throws Exception {
        check(setPriceHandShakePeriods(interval, timeout), "doSetPriceHandShakePeriods");
    }    

    protected void messageReceived(int messageID, int length) {
        processMessage(messageID, length, msgBuff);
    }

    protected void processMessage(int messageID, int length, byte[] buffer) {
        try {
            switch (messageID) {
	            case APICallbacks.MID_HOST_LINK_CHANGE:
	            	System.out.println("Host Link Status Change message ID");
	                break;
	            case APICallbacks.MID_PRICE_LINK_CHANGE:
	            	System.out.println("Price Link Status Change message ID");
	                break;
	            case APICallbacks.MID_LOGON_STATUS:
	            	System.out.println("Logon Status message ID");
	                break;
	            case APICallbacks.MID_MESSAGE:
	                System.out.println("User Message message ID");
	                break;
	            case APICallbacks.MID_DOWNLOAD_COMPLETE:
	            	System.out.println("Download Complete message ID");
	                getAPIData();
	                break;
	            case APICallbacks.MID_CONTRACT_ADDED:
	                System.out.println("Contract Added message ID");
	                break;
	            case APICallbacks.MID_CONTRACT_DELETED:
	                System.out.println("Contract Deleted message ID");
	                break;
	            case APICallbacks.MID_BLANK_PRICE:
	                System.out.println("Price blank message ID");
	                break;
	            case APICallbacks.MID_PRICE:
	            	System.out.println("Price Change message ID");
	                doGetContractPrices(buffer);
	                break;
	            case APICallbacks.MID_SETTLEMENT_PRICE:
	                System.out.println("Settlement Price message ID");
	                break;
	            case APICallbacks.MID_DOM_UPDATE:
	                System.out.println("DOM update message ID");
	                //doGetContractPrices(buffer);
	                break;
	            case APICallbacks.MID_STATUS:
	                System.out.println("Status Update message ID");
	                break;
	            case APICallbacks.MID_AT_BEST_ID:
	                System.out.println("At Best message ID");
	                break;
	            case APICallbacks.MID_SUBSCRIBER_DEPTH:
	                System.out.println("Subscriber Depth message ID");
	                break;
	            case APICallbacks.MID_GENERIC_PRICE:
	                System.out.println("Generic Price message ID");
	                break;
	            case APICallbacks.MID_FILL:
	                System.out.println("Fill message ID");
	                break;
	            case APICallbacks.MID_ORDER:
	            	System.out.println("Order message ID");
	                doGetOrderByID(buffer);
	                break;
	            case APICallbacks.MID_FORCED_LOGOUT:
	            	System.out.println("End of Day message ID");
	                break;
	            case APICallbacks.MID_EXCHANGE_RATE:
	                System.out.println("Exchange Rate Updated message ID");
	                break;
	            case APICallbacks.MID_CONNECTIVITY_STATUS:
	                System.out.println("Connectivity Status Update message ID");
	                break;
	            case APICallbacks.MID_ORDER_CANCEL_FAILURE_ID:
	                System.out.println("Order Cancellation Timeout message ID");
	                break;
	            case APICallbacks.MID_MEMORY_WARNING:
	                System.out.println("Memory warning message ID");
	                break;
	            case APICallbacks.MID_STRATEGY_CREATION_RECEIVED:
	                System.out.println("Strategy creation successfullyReceived ID");
	                break;
	            case APICallbacks.MID_STRATEGY_CREATION_FAILURE:
	                System.out.println("Strategy creation failure ID");
	                break;
	            case APICallbacks.MID_ORDER_SENT_TIMEOUT:
	                System.out.println("Order Sent Timeout ID");
	                break;
	            case APICallbacks.MID_ORDER_QUEUED_TIMEOUT:
	                System.out.println("Order Queued Timeout ID");
	                break;
	            case APICallbacks.MID_RESET_ORDERBOOK:
	                System.out.println("Order Book reset ID");
	                break;
	            default:
	                System.out.println("Default Message ID: " + messageID + ", length=" + length);
            }
        } catch (Exception e) {
            System.out.println(
            	"Error processing callback message: " + messageID + ". Error: " + e.toString()
            );
        }
    }
    
    public void stop() {
    	try {
    		doLogoff();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
