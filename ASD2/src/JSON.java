import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JSON {
    public class JSonAPI {
        public static void main(String[] args) throws IOException {
            String NIM;
            String Name;
            String Address;
            String Phone;

            ArrayList<Students> _studentTI = new ArrayList<>();
            try {
                Students s = new Students(); //s =(nim:'',nama:'',address:'',phone:'');
                Scanner _input = new Scanner(System.in);
                System.out.println("Masukkan NIM : ");
                NIM = _input.nextLine();
                System.out.println("Masukkan Nama : ");
                Name = _input.nextLine();
                System.out.println("Masukkan Alamat : ");
                Address = _input.nextLine();
                System.out.println("Masukkan Phone : ");
                Phone = _input.nextLine();

                s.set_nim(NIM);
                s.set_name(Name);
                s.set_address(Address);
                s.set_phone(Phone);
                _studentTI.add(s);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int j=0;j< _studentTI.size();j++){
                System.out.println(_studentTI.get(j).get_nim() + " - "+_studentTI.get(j).get_name() + " - "+
                        _studentTI.get(j).get_address() + " - "+_studentTI.get(j).get_phone() + " - ");
            }

            //mengubah arraylist menjadi format json

            JSONArray jsonStudent = new JSONArray();
            JSONObject myJObject = new JSONObject();
            myJObject.put("nim",_studentTI.get(0).get_nim());
            myJObject.put("name",_studentTI.get(0).get_name());
            myJObject.put("address",_studentTI.get(0).get_address());
            myJObject.put("phone",_studentTI.get(0).get_phone());
            jsonStudent.put(myJObject);

            System.out.println(jsonStudent.toString());

            //send Student data to database cloud
            ConnectURI myUrlBuilder = new ConnectURI();
            URL myAddress = ConnectURI.buildURL("https://harber.mimoapps.xyz/fromjava.php");
            myUrlBuilder.postJSON(myAddress,jsonStudent.toString());
        }
    }
}
