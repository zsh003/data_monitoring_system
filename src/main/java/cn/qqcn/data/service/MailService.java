package cn.qqcn.data.service;

public interface MailService {
    boolean sendSimpleText(String to, String subject, String content);

    boolean sendWithHtml(String to, String subject, String html);

    boolean sendWithImageHtml(String to, String subject, String html, String[] cids, String[] filePaths);

    boolean sendWithWithEnclosure(String to, String subject, String content, String[] filePaths);
}
