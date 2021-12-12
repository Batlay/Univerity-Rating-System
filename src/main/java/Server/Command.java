package Server;

import java.io.Serializable;

public enum Command implements Serializable {
    READ,
    READSUB,
    READGROUP,
    READGROUPCB,
    READSUBJECTCB,
    READMARK,
    READSPEC,
    READFACULTY,
    READYEAR,
    READDATE,
    READEMAIL,
    READSTUDYPLAN,
    READID,
    STUDENTINFO,
    SELECTGROUP,
    TEACHERMARK,
    TEACHERTABLE,
    TEACHERINFO,
    TEACHERSUBJECTS,
    ADMINSTUDENTS,
    ADMINTEACHER,
    LOGINTEACHER,
    LOGINSTUDENT,
    LOGINADMIN,
    AVG_KT,
    UPDATE,

    EXIT
}