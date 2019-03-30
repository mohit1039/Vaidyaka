package medic.vaidyaka.Model;

public class Register {

    String name,phoneno,docname,docphone,editvalue,email,age;

    public Register() {
    }

    public Register(String name, String phoneno, String docname, String docphone, String editvalue) {
        this.name = name;
        this.phoneno = phoneno;
        this.docname = docname;
        this.docphone = docphone;
        this.editvalue = editvalue;
    }

    public String getEditvalue() {
        return editvalue;
    }

    public void setEditvalue(String editvalue) {
        this.editvalue = editvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getDocphone() {
        return docphone;
    }

    public void setDocphone(String docphone) {
        this.docphone = docphone;
    }
}
