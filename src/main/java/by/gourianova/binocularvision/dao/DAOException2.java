package by.gourianova.binocularvision.dao;

public class DAOException2 extends Exception {
//TODO: know serialVersionUID, stay only one  DAOException class
	//private static final long serialVersionUID = 5579627996438039829L;

	public DAOException2() {
		super();
	}
	
	public DAOException2(String message) {
		super(message);
	}
	
	public DAOException2(Exception e) {
		super(e);
	}

	public DAOException2(String message, Exception e) {
		super(message, e);
	}

}
