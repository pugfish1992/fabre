package com.pugfish1992.fabre.data.task;

import android.support.annotation.NonNull;

import com.pugfish1992.fabre.data.Storage;
import com.pugfish1992.fabre.data.comment.Comment;

import java.util.List;

/**
 * Created by daichi on 11/30/17.
 */

public interface Tasks extends Storage<Task, TaskDiff> {

    @NonNull Task insert(String name,
                         @NonNull Comment primaryComment);

    @NonNull Task insert(String name,
                         @NonNull Comment primaryComment,
                         @NonNull List<Comment> comments);
}
