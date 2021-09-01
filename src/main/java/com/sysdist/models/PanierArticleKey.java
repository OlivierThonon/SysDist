package com.sysdist.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PanierArticleKey implements Serializable {

    @Column(name="article_id")
    Long articleId;

    @Column(name="panier_id")
    Long panierId;

    public PanierArticleKey(Long articleId, Long panierId) {
        this.articleId = articleId;
        this.panierId = panierId;
    }

    public PanierArticleKey() {

    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getPanierId() {
        return panierId;
    }

    public void setPanierId(Long panierId) {
        this.panierId = panierId;
    }

    @Override
    public int hashCode() {
        return (int)this.panierId.hashCode() + (int)this.articleId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        PanierArticleKey other = (PanierArticleKey)obj;
        if(this.articleId == other.articleId && this.panierId == other.panierId)
            return(true);
        else
            return(false);
    }
}
