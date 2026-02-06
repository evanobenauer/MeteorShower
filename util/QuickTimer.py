import math
import time


class QuickTimer:

    def __init__(self):
        self.start_time_ns: int = -1


    def start(self):
        if not self.is_started():
            self.restart()

    def restart(self):
        self.start_time_ns = time.time_ns()

    def is_started(self) -> bool:
        return self.get_start_time_nanos() > -1

    #NanoSeconds (10e-9)
    def has_time_passed_ns(self,ns: int) -> bool:
        return self.is_started() and ns < self.get_time_nanos()

    def get_time_nanos(self) -> int:
        return time.time_ns() - self.get_start_time_nanos()

    def get_start_time_nanos(self) -> int:
        return self.start_time_ns


    #Seconds
    def has_time_passed_s(self,s: float) -> bool:
        return self.is_started() and s < (self.get_time_nanos() / math.pow(10,9))

    def get_time_s(self) -> int:
        return (time.time_ns() - self.get_start_time_nanos()) / math.pow(10,9)