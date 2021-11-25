package com.github.phillipkruger.model;

import io.smallrye.graphql.api.Scalar;
import io.smallrye.graphql.api.ToScalar;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Person implements Serializable {
    static final long serialVersionUID = 42L;
    
    @ToScalar(Scalar.Int.class)
    private Long id;
    
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate birthDate;
    
    @ToScalar(Scalar.String.class)
    private Email email;

    private String locale;

    private String title;

    private List<String> names;

    private List<String> nicknames;

    private String surname;

    private String username;

    private String idNumber;

    private List<URL> coverphotos;

    private List<URL> profilePictures;

    private Gender gender;

    private String favColor;

    private List<Address> addresses;
    
    private List<String> emailAddresses;
    
    private List<Phone> phoneNumbers;
    
    private List<ImClient> imClients;

    private List<SocialMedia> socialMedias;

    private URL website;
  
    private List<String> taglines;

    private String biography;

    private String organization;

    private String occupation;
    
    private List<String> interests;

    private List<String> skills;

    private List<Relation> relations;

    private LocalDate joinDate;

    private String maritalStatus;
    
    private List<CreditCard> creditCards;

    private String userAgent;

    private CurencyCode curencyCode;
    
    public void addName(String name){
        if(names==null)names = new LinkedList<>();
        names.add(name);
    }

    public void addNickName(String nickname){
        if(nicknames==null)nicknames = new LinkedList<>();
        nicknames.add(nickname);
    }

    public void addCoverPhoto(URL coverphoto){
        if(coverphotos==null)coverphotos = new LinkedList<>();
        coverphotos.add(coverphoto);
    }

    public void addProfilePicture(URL profilePicture){
        if(profilePictures==null)profilePictures = new LinkedList<>();
        profilePictures.add(profilePicture);
    }

    public void addAddress(Address address){
        if(addresses==null)addresses = new LinkedList<>();
        addresses.add(address);
    }

    public void addEmailAddress(String emailAddress){
        if(emailAddresses==null)emailAddresses = new LinkedList<>();
        emailAddresses.add(emailAddress);
    }

    public void addPhoneNumber(Phone phoneNumber){
        if(phoneNumbers==null)phoneNumbers = new LinkedList<>();
        phoneNumbers.add(phoneNumber);
    }

    public void addImClient(ImClient imClient){
        if(imClients==null)imClients = new LinkedList<>();
        imClients.add(imClient);
    }

    public void addSocialMedia(SocialMedia socialMedia){
        if(socialMedias==null)socialMedias = new LinkedList<>();
        socialMedias.add(socialMedia);
    }

    public void addTagline(String tagline){
        if(taglines==null)taglines = new LinkedList<>();
        taglines.add(tagline);
    }

    public void addInterest(String interest){
        if(interests==null)interests = new LinkedList<>();
        interests.add(interest);
    }

    public void addSkill(String skill){
        if(skills==null)skills = new LinkedList<>();
        skills.add(skill);
    }

    public void addRelationship(Relation relation){
        if(relations==null)relations = new LinkedList<>();
        relations.add(relation);
    }

    public void addCreditCard(CreditCard card){
        if(creditCards==null)creditCards = new LinkedList<>();
        creditCards.add(card);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Email getEmail() {
        return new Email(getEmailAddresses().get(0));
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=URL.class)
    public List<URL> getCoverphotos() {
        return coverphotos;
    }

    public void setCoverphotos(List<URL> coverphotos) {
        this.coverphotos = coverphotos;
    }

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=URL.class)
    public List<URL> getProfilePictures() {
        return profilePictures;
    }

    public void setProfilePictures(List<URL> profilePictures) {
        this.profilePictures = profilePictures;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = favColor;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<ImClient> getImClients() {
        return imClients;
    }

    public void setImClients(List<ImClient> imClients) {
        this.imClients = imClients;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<SocialMedia> getSocialMedias() {
        return socialMedias;
    }

    public void setSocialMedias(List<SocialMedia> socialMedias) {
        this.socialMedias = socialMedias;
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getTaglines() {
        return taglines;
    }

    public void setTaglines(List<String> taglines) {
        this.taglines = taglines;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @ElementCollection(targetClass=String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public CurencyCode getCurencyCode() {
        return curencyCode;
    }

    public void setCurencyCode(CurencyCode curencyCode) {
        this.curencyCode = curencyCode;
    }
}