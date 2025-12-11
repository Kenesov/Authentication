package vetro.uz.authentication.domain;

import java.util.ArrayList;
import java.util.List;

import vetro.uz.authentication.data.UserData;
import vetro.uz.authentication.data.sources.MyDataPreferences;

public class AppRepositoryImpl implements AppRepository{
    private static AppRepositoryImpl instance;
    private MyDataPreferences pref = MyDataPreferences.getInstance();
    private List<UserData> ls = new ArrayList<>();
    private AppRepositoryImpl(){parse();}
    public static AppRepository getInstance(){return instance;}
    public static void init(){
        if (instance == null){
            instance = new AppRepositoryImpl();
        }
    }

    private void parse(){
        String st = pref.getData();
        if (st == null) return;
        String[] mass = st.split("&");

        for (String s: mass){
            if (s.isEmpty()) continue;
            String[] x = s.split("#");
            if (x.length < 5) continue;
            boolean active = Boolean.parseBoolean(x[4]);
            ls.add(new UserData(x[0], x[1], x[2], x[3], active));
        }
    }
    private void save(){
        StringBuilder str = new StringBuilder();
        ls.forEach(userData -> {
            str.append(userData.getFirstName())
                    .append("#")
                    .append(userData.getLastName())
                    .append("#")
                    .append(userData.getLogin())
                    .append("#")
                    .append(userData.getPassword())
                    .append("#")
                    .append(userData.getisActive())
                    .append("&");
        });
        pref.saveData(str.toString());
    }

    @Override
    public int register(String login, String password) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getLogin().equals(login)) return -1;
        }
        ls.add(new UserData("","",login,password, true));
        setActive(ls.size()-1, true);

        save();
        return ls.size()-1;
    }

    @Override
    public int checkUser(String login, String password) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getLogin().equals(login) && ls.get(i).getPassword().equals(password)) {
                setActive(i, true);
                return i;
            }
        }
        return -1;
    }

    @Override
    public UserData getUserDataByIndex(int index) {
        if (index >= ls.size()) return null;
        return ls.get(index);
    }

    @Override
    public int getCurrentUserIndex() {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getisActive()) return i;
        }
        return -1;
    }

    @Override
    public void update(int index, String newFirstName, String newLastName) {
        if (index >= ls.size()) return;
        ls.get(index).setFirstName(newFirstName);
        ls.get(index).setLastName(newLastName);
        ls.get(index).setActive(true);
        save();
    }

    @Override
    public void deleteAccount(int index) {
        if (index < 0 || index >= ls.size()) return;
        ls.remove(index);
        save();
    }

    @Override
    public boolean isActive(int index) {
        if (index < 0 || index >= ls.size()) return false;
        return ls.get(index).getisActive();
    }

    @Override
    public void setActive(int index, boolean bool) {
        if (index < 0 || index >= ls.size()) return;

        for (int i = 0; i < ls.size(); i++) {
            ls.get(i).setActive(false);
        }

        ls.get(index).setActive(bool);

        save();
    }

    @Override
    public void logout(int index) {
        if (index < 0 || index >= ls.size()) return;
        ls.get(index).setActive(false);
        save();
    }


}
