package com.pugfish1992.fabre.data.comment;

import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Storage;

import java.util.Calendar;

/**
 * Created by daichi on 11/29/17.
 */

public interface Comments extends Storage<Comment, CommentAlter> {
    @NonNull Comment insert(String text, @NonNull Calendar createdAt, @NonNull Calendar lastModifiedAt);
}
