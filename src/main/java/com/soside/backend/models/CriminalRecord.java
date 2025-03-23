package com.soside.backend.models;
import com.soside.backend.enums.AppealStatus;
import com.soside.backend.enums.ParoleStatus;
import com.soside.backend.enums.RehabilitationStatus;
import com.soside.backend.enums.SentenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "criminalRecord")
public class CriminalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    private LocalDate convictionDate;
    private String offenseDescription;
    private String courtName;
    private String judgeName;
    private String sentence;

    @Enumerated(EnumType.STRING)
    private SentenceType sentenceType;

    private String sentenceDuration;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private ParoleStatus paroleStatus;

    @Enumerated(EnumType.STRING)
    private RehabilitationStatus rehabilitationStatus;

    private String caseNumber;
    private String prosecutorName;
    private String defenseAttorneyName;

    @Enumerated(EnumType.STRING)
    private AppealStatus appealStatus;

    private String notes;
    private String authorizedBy;
    private LocalDateTime consultationDate;
    private String consultationReason;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getConvictionDate() {
        return convictionDate;
    }

    public void setConvictionDate(LocalDate convictionDate) {
        this.convictionDate = convictionDate;
    }

    public String getOffenseDescription() {
        return offenseDescription;
    }

    public void setOffenseDescription(String offenseDescription) {
        this.offenseDescription = offenseDescription;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public SentenceType getSentenceType() {
        return sentenceType;
    }

    public void setSentenceType(SentenceType sentenceType) {
        this.sentenceType = sentenceType;
    }

    public String getSentenceDuration() {
        return sentenceDuration;
    }

    public void setSentenceDuration(String sentenceDuration) {
        this.sentenceDuration = sentenceDuration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ParoleStatus getParoleStatus() {
        return paroleStatus;
    }

    public void setParoleStatus(ParoleStatus paroleStatus) {
        this.paroleStatus = paroleStatus;
    }

    public RehabilitationStatus getRehabilitationStatus() {
        return rehabilitationStatus;
    }

    public void setRehabilitationStatus(RehabilitationStatus rehabilitationStatus) {
        this.rehabilitationStatus = rehabilitationStatus;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getProsecutorName() {
        return prosecutorName;
    }

    public void setProsecutorName(String prosecutorName) {
        this.prosecutorName = prosecutorName;
    }

    public String getDefenseAttorneyName() {
        return defenseAttorneyName;
    }

    public void setDefenseAttorneyName(String defenseAttorneyName) {
        this.defenseAttorneyName = defenseAttorneyName;
    }

    public AppealStatus getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(AppealStatus appealStatus) {
        this.appealStatus = appealStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }
}



