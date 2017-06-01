package client.model;
import java.util.ArrayList;


public class UserList {


	

		private ArrayList<User> listUser= new ArrayList<User>();

		public UserList(ArrayList<User> listUser) {
			super();
			this.setListUser(listUser);
		}

		public ArrayList<User> getListUser() {
			return listUser;
		}

		public void setListUser(ArrayList<User> listUser) {
			this.listUser = listUser;
		}



}