package by.gourianova.binocularvision.dao;

public class DAOException extends Exception {
//TODO: know serialVersionUID, stay only one  DAOException class
	//private static final long serialVersionUID = 5579627996438039829L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
	/*
	 public class DAOException extends Exception {
		public DAOException() {
		}

		public DAOException(String message) {
			super(message);
		}

		public DAOException(String message, Throwable cause) {
			super(message, cause);
		}

		public DAOException(Throwable cause) {
			super(cause);
		}
	}

*/