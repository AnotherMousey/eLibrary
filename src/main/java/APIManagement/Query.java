package APIManagement;

public class Query {
    private String inTitle;
    private String inAuthor;
    private String inPublisher;
    private String subject;
    private String isbn; // International Standard Book Number
    private String lccn; // Library of Congress Control Number
    private String oclc; // Online Computer Library Center
    private String Standardization(String keyword) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < keyword.length(); i++) {
            char ch = keyword.charAt(i);
            if(ch == ' ') {
                result.append("%20");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
    public String getInTitle() {
        return Standardization(inTitle.toLowerCase());
    }
    public void setInTitle(String inTitle) {
        this.inTitle = inTitle;
    }
    public String getInAuthor() {
        return Standardization(inAuthor.toLowerCase());
    }
    public void setInAuthor(String inAuthor) {
        this.inAuthor = inAuthor;
    }
    public String getInPublisher() {
        return Standardization(inPublisher.toLowerCase());
    }
    public void setInPublisher(String inPublisher) {
        this.inPublisher = inPublisher;
    }
    public String getSubject() {
        return Standardization(subject.toLowerCase());
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getLccn() {
        return lccn;
    }
    public void setLccn(String lccn) {
        this.lccn = lccn;
    }
    public String getOclc() {
        return oclc;
    }
    public void setOclc(String oclc) {
        this.oclc = oclc;
    }
    public Query() {
        inTitle = "";
        inAuthor = "";
        inPublisher = "";
        subject = "";
        isbn = "";
        lccn = "";
        oclc = "";
    }
    public String getQuery() {
        String query = "";
        if(!this.getInTitle().isEmpty()){
            query += "intitle:" + this.getInTitle();
        }
        if(!this.getInAuthor().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "inauthor:" + this.getInAuthor();
        }
        if(!this.getInPublisher().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "inpublisher:" + this.getInPublisher();
        }
        if(!this.getSubject().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "subject:" + this.getSubject();
        }
        if(!this.getIsbn().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "isbn:" + this.getIsbn();
        }
        if(!this.getLccn().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "lccn:" + this.getLccn();
        }
        if(!this.getOclc().isEmpty()){
            if(!query.isEmpty()) {
                query += "+";
            }
            query += "oclc:" + this.getOclc();
        }
        return query;
    }
}
