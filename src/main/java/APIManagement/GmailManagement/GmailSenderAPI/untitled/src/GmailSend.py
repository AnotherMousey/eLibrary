import sys
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


def send_email(to, subject, message):
    port = 465

    smtp_server = "smtp.gmail.com"
    sender_email = "hoangthanhbinh2006@gmail.com"
    password = "zoyfceabaneuhily"
    receiver_email = to

    msg = MIMEMultipart()
    msg["Subject"] = subject
    msg["From"] = sender_email
    msg["To"] = receiver_email
    msg.attach(MIMEText(message, 'plain'))

    with smtplib.SMTP_SSL(smtp_server, port) as server:
        server.ehlo()
        server.login(sender_email, password)
        server.sendmail(sender_email, receiver_email, msg.as_string())
        print("Email sent!")
        server.quit()

if __name__ == "__main__":
    send_email(sys.argv[1], sys.argv[2], sys.argv[3])