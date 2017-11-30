package com.pugfish1992.fabre.data.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pugfish1992.fabre.data.Record;
import com.pugfish1992.fabre.data.comment.Comment;

import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

public interface Task extends Record {
    @Nullable String name();
    @NonNull Comment primaryComment();
    @NonNull List<Comment> comments();
}
