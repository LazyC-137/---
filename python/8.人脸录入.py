#导入cv模块
import cv2 as cv
import sys
#读取摄像头
cap = cv.VideoCapture(0)

flag = 1
num = 1

while(cap.isOpened()):#检测摄像头是否开启
    ret_flag,Vshow = cap.read()#得到每帧图像
    cv.imshow("Capture_Test",Vshow)#显示图像
    k = cv.waitKey(1) & 0xFF#按键判断
    if k == ord('s'):#保存
        cv.imwrite("D:/Work/PyCharm Community Edition 2022.3.1/pythonProject/library/"+str(num)+"."+sys.argv[0]+".jpg",Vshow)
        print(sys.argv[1])
        print("success to save"+str(num)+".jpg")
        print("----------------")
        num += 1
    elif k == ord(' '):#退出
        break
#释放内存
cv.destroyAllWindows()
#释放摄像头
cap.release()