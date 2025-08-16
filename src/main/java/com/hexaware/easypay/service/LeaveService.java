package com.hexaware.easypay.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.easypay.dto.LeaveCalcDTO;
import com.hexaware.easypay.entity.LeaveCalc;
import com.hexaware.easypay.entity.LeaveRequest;
import com.hexaware.easypay.exception.DefaultException;
import com.hexaware.easypay.repository.LeaveCalcRepository;
import com.hexaware.easypay.repository.LeaveRequestRepository;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import jakarta.transaction.Transactional;

//For manager use only!

@Service
public class LeaveService implements  ILeaveService {

    private static final Logger log = LogManager.getLogger(LeaveService.class);

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveCalcRepository leaveCalcRepository;

    public List<LeaveRequest> getLeaveRequestsByEmployee(int empId) {
        return leaveRequestRepository.findByEmpId(empId);
    }

    @Transactional
    @Override
    public void approveLeaveRequest(UUID reqId, LeaveCalcDTO dto1) {
        try {
            LeaveRequest leaveRequest = leaveRequestRepository.findById(reqId)
                    .orElseThrow(() -> new DefaultException("Leave request is not found!"));
            LeaveCalc leaveCalc = leaveCalcRepository.findById(reqId)
                    .orElseThrow(() -> new DefaultException("Leave calculation is not found!"));

            if ("approved".equalsIgnoreCase(leaveRequest.getApprovalStatus())) {
                throw new DefaultException("Leave request already approved.");
            }

            // Ensuring that start < end date
            LocalDate start = leaveRequest.getStartDate();
            LocalDate end = leaveRequest.getEndDate();

            if (start.isAfter(end)) {
                throw new DefaultException("Please enter the start date first, and end date next!");
            }

            // getting paid and unpaid leaves
            int paid = dto1.getPaidLeaves();
            int unpaid = dto1.getUnpaidLeaves();

            if (paid < 0 || unpaid < 0) {
                throw new DefaultException("Leave counts cannot be negative.");
            }
            int total = leaveCalc.getTotalLeaves();

            if (total <= 0) {
                throw new DefaultException(
                        "Total leaves not found or invalid in leave calculation. Please check the request.");
            }

            if (paid + unpaid != total) {
                throw new DefaultException("Paid + Unpaid  not equal to Total leaves.");
            }

            leaveCalc.setPaidLeaves(paid);
            leaveCalc.setUnpaidLeaves(unpaid);
            leaveCalcRepository.save(leaveCalc);

            leaveRequest.setApprovalStatus("approved");
            leaveRequest.setApprovalRejectionReason("Your leave was approved and the details have been sent back.");
            leaveRequest.setApproveDate(LocalDate.now());

            leaveRequestRepository.save(leaveRequest);
            log.info("Leave approved for reqId: {} with paid={}, unpaid={}", reqId, paid, unpaid);
        } catch (DefaultException e) {
            log.error("Approval was unsucessful for {} due to an error", reqId);
            throw e;
        }

    }

    @Transactional
    @Override
    public void rejectLeaveRequest(UUID reqId) {

        try {

            LeaveRequest leaveRequest = leaveRequestRepository.findById(reqId)
                    .orElseThrow(() -> new DefaultException("Leave request is not found!"));

           

            leaveRequest.setApprovalStatus("rejected");
            leaveRequest.setApprovalRejectionReason("Leave request given for invalid reason.");
            leaveRequestRepository.save(leaveRequest);
            log.info("Leave rejected for reqId: {}", reqId);

        } catch (DefaultException e) {
            log.error("Rejection was unsucessful for {} due to an error", reqId);
            throw e;
        }

    }

    @Override
    public Map<String,Integer> getTotalLeaveDaysbyUUID(UUID reqId) {

        LeaveRequest lr = leaveRequestRepository.findById(reqId)
                .orElseThrow(() -> new DefaultException("Invalid UUID, please enter the correct one again!"));
        
         LeaveCalc leaveCalc = leaveCalcRepository.findById(reqId).orElseThrow(() -> new DefaultException("Leave calculations is not found!"));

   

        LocalDate start = lr.getStartDate();
        LocalDate end = lr.getEndDate();

        int workingDays = 0;

        Properties props = new Properties();
        props.setProperty("calendar", HolidayCalendar.UNITED_STATES.name()); //INDIA not available so using USA
        HolidayManager holidayManager = HolidayManager.getInstance(props);

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dow = date.getDayOfWeek();
            boolean isWeekend = dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY;
            boolean isHoliday = holidayManager.isHoliday(date);

            if (!isWeekend && !isHoliday) {
                workingDays++;
            }

                  leaveCalc.setTotalLeaves(workingDays);
                  leaveCalcRepository.save(leaveCalc);
        }

        return  Map.of("Effective days: ", workingDays);

    }

}
