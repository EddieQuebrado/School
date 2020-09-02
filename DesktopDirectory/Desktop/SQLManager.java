import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;

public class SQLManager {
	
	private int itemsCreated;
	private int purchCreated;
	private int shipmCreated;
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: SQLManager
	////
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	public SQLManager(Connection conn, String[] args) {
		String selectMethod = args[0]; //Selects method to use
		int itemCode = Integer.parseInt(args[1]); //Selects itemCode
		itemsCreated = 0; //Amount of times CreateItem was called
		purchCreated = 0; //Amount of times CreatePurchase
		shipmCreated = 0; //Amount of times CreateShipment
		if(selectMethod.equals("/?")) {
			printUsage();
		} else if(selectMethod.equals("CreateItem")) {
			// Call CreateItem procedure
			String itemDescription = args[2];
			Double price = Double.parseDouble(args[3]);
			CreateItem(conn, itemCode, itemDescription, price);
		} else if(selectMethod.equals("CreatePurchase")) {
			// Call CreatePurchase procedure
			int purchaseQuantity = Integer.parseInt(args[2]);
			CreatePurchase(conn, itemCode, purchaseQuantity);
		} else if(selectMethod.equals("CreateShipment")) {
			// Call CreateShipment procedure
			int shipmentQuantity = Integer.parseInt(args[2]);
			String shipmentDate = args[3];
			CreateShipment(conn, itemCode, shipmentQuantity, shipmentDate);
		} else if(selectMethod.equals("GetItems")) {
			// Call GetItems procedure
			GetItems(conn, itemCode);
		} else if(selectMethod.equals("GetShipments")) {
			// Call GetShipments procedure
			GetShipments(conn, itemCode);
		} else if(selectMethod.equals("GetPurchases")) {
			// Call GetPurchases procedure
			GetPurchases(conn, itemCode);
		} else if(selectMethod.equals("ItemsAvailable")) {
			// Call ItemsAvailable procedure
			ItemsAvailable(conn, itemCode);
		} else if(selectMethod.equals("UpdateItem")) {
			// Call UpdateItem procedure
			Double price = Double.parseDouble(args[2]);
			UpdateItem(conn, itemCode, price);
		} else if(selectMethod.equals("DeleteItem")) {
			// Call DeleteItem procedure
			DeleteItem(conn, itemCode);
		} else if(selectMethod.equals("DeleteShipment")) {
			// Call DeleteShipment procedure
			DeleteShipment(conn, itemCode);
		} else if(selectMethod.equals("DeletePurchase")) {
			// Call DeletePurchase procedure
			DeletePurchase(conn, itemCode);
		} else {
			System.out.println("FIRST ARGUMENT IS NOT A VALID ARGUMENT\n"
					+ "OPENING COMMAND LINE USAGE\n"
					+ "-----------------------------------------------------\n");
			printUsage();
		}
		
	}
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length < 1 || args.length > 4) {
			printUsage();
			System.exit(1);
		}
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println();
			System.out.println("JDBC driver loaded");
			System.out.println();
			Connection conn = makeConnection();
			new SQLManager(conn, args);
			System.out.println("Final Result");
			conn.close();
			System.out.println();
			System.out.println("Database [test db] connection closed");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: SQL Create Methods
	////		- CreateItem
	////		- CreatePurchase
	////		- CreateShipment
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @param String ItemDescription
	 * @param double Price
	 */
	public static void CreateItem(Connection conn, int ItemCode, String ItemDescription, double Price) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("Call createitem(" + ItemCode + "," + ItemDescription + "," + Price + ")");
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int itemCode
	 * @param int purchaseQuantity
	 */
	public static void CreatePurchase(Connection conn, int itemCode, int purchaseQuantity) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("Call createitem(" + itemCode + "," + purchaseQuantity + ")");
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/**
	 * 
	 * @param conn
	 * @param itemCode
	 * @param shipmentQuantity
	 * @param shipmentDate
	 */
	public void CreateShipment(Connection conn, int itemCode, int shipmentQuantity, String shipmentDate) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("Call createShipment(" + itemCode + "," + shipmentQuantity + "," + shipmentDate + ")");
			increaseShipments();
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: SQL Get Methods
	////		- GetItems
	////		- GetPurchases
	////		- GetShipments
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param conn
	 * @param itemCode
	 * @return ResultSet
	 */
	public ResultSet GetItems(Connection conn, int itemCode) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM cs1.Class");
			return rs; // return statement was needed
			// TODO: Decide what to do with result set.
			
//			Now do something with the ResultSet ....
//			boolean rowsLeft = true;
//			rs.first();
//			System.out.println("Select * FROM Class");
//			while (rowsLeft) {
//				System.out.println(rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4) + ":" + rs.getString(5));
//				rowsLeft = rs.next();
//			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return ResultSet
	 */
	public ResultSet GetShipments(Connection conn, int itemCode) {
		//allow % to be used for getting all shipments
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return ResultSet
	 */
	public ResultSet GetPurchases(Connection conn, int itemCode) {
		//allow % to be used for getting all shipments
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return null;
	}
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: SQL Alter ItemTable Methods
	////		- ItemsAvailable
	////		- UpdateItem
	////
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return Result set
	 */
	public ResultSet ItemsAvailable(Connection conn, int itemCode) {
		//allow % to be used for getting all shipments
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @param double price
	 * @return int
	 */
	public int UpdateItem(Connection conn, int itemCode, double price) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return 0;
	}
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: SQL Delete Methods
	////		- DeleteItem
	////		- DeleteShipment
	////		- DeletePurchase
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return int
	 */
	public int DeleteItem(Connection conn, int itemCode) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return int
	 */
	public int DeleteShipment(Connection conn, int itemCode) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param Connection conn
	 * @param int ItemCode
	 * @return int
	 */
	public int DeletePurchase(Connection conn, int itemCode) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
		return 0;
	}
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: Methods to keep track of current items,
	////	   purchases, shipments. They can be compared 
	////	   to user inputed itemCodes
	////		- increaseItems
	////		- increasePurchases
	////		- increaseShipments
	////		- getItemsCreated
	////		- getPurchasesCreated
	////		- getShipmentsCreated
	////------------------------------------------------------
	//////////////////////////////////////////////////////////

	public void increaseItems() {
		itemsCreated++;
	}
	
	public void increasePurchases() {
		purchCreated++;
	}
	
	public void increaseShipments() {
		shipmCreated++;
	}
	
	public int getItemsCreated() {
		return itemsCreated++;
	}
	
	public int getPurchasesCreated() {
		return purchCreated;
	}
	
	public int getShipmentsCreated() {
		return shipmCreated;
	}
	
	//////////////////////////////////////////////////////////
	////------------------------------------------------------
	////  XXX: Extra Methods
	////		- makeConnection
	////		- printUsage
	////		- GetData
	////------------------------------------------------------
	//////////////////////////////////////////////////////////
	
	/**
	 * Creates a connection to MySQL Workbench on the Boise State onyx server
	 * @return Connection
	 */
	public static Connection makeConnection() {
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:5902/test?verifyServerCertificate=false&useSSL=true", "msandbox",
					"adminpass");
			// Do something with the Connection
			System.out.println("Database [test db] connection succeeded!");
			System.out.println();
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	/**
	 * Prints command line usage for the program to work
	 */
	public static void printUsage() {
		String str = "Command Line Usage:\n";
		str +=       "java project /?\n"
				+    "java project CreateItem <itemCode> <itemDescription> <price>\n"
				+    "java project CreatePurchase <itemCode> <PurchaseQuantity>\n"
				+    "java project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>\n"
				+ 	 "java project GetItems <itemCode>\n"
				+ 	 "java project GetShipments <itemCode>\n"
				+ 	 "java project GetPurchases <itemCode>\n"
				+ 	 "java project ItemsAvailable <itemCode>\n"
				+ 	 "java project UpdateItem <itemCode> <price>\n"
				+ 	 "java project DeleteItem <itemCode>\n"
				+ 	 "java project DeleteShipment <itemCode>\n"
				+ 	 "java project DeletePurchase <itemCode>";
		System.out.println(str);
	}
	
	public static void GetData(Connection conn) {
		ResultSet rs = null;
		Statement stmt = null;
		boolean rowsLeft = true;
		try {
				stmt = conn.createStatement();
				//stmt.execute("Call GetData();");
				CallableStatement cs = conn.prepareCall("Call GetData()");
				System.out.println("Call cs1.GetData();");
				cs.getMoreResults();
				rs = cs.getResultSet();
				rs.first();
				while (rowsLeft) {
					System.out.println(rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4) + ":" + rs.getString(5));
					rowsLeft = rs.next();
				}
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}
				stmt = null;
			}
		}
	}	
	
}

		