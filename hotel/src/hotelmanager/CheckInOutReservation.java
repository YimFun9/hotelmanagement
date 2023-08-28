package hotelmanager;

import java.time.LocalDate;
import java.util.Scanner;

public class CheckInOutReservation {
	private final Scanner sc = new Scanner(System.in);
	ManagementSystem ms;
	private int countTry = 0; // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용 카운트

	public CheckInOutReservation(ManagementSystem ms) {
		this.ms = ms;
	}

	// 체크아웃 외에 전부 수정했습니다.
	// 임태경 //방번호로 바로 들어오는 예약함수
	void inputReservation(int selNum, Floor floor) {
		int i = selNum / 100 - ms.whoFloor(floor);
		int j = selNum % 100 - 1;
		if (!(UtilityHotelFuntion.checkRoomNum2(selNum, floor))) {
			System.out.println("본 건물은 5월20일부터 예약이 가능합니다. ");
			if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isPossible() == true) {
				System.out.println("예약할 고객의 정보를 아래에 입력해주세요.");
				System.out.println("이      름: ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연 락 처 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());
				System.out.println("예약하고자 하는 년,월,일을 차례대로 입력해주세요.");
				LocalDate startDate = UtilityHotelFuntion.exceptionDataTest(floor);
				System.out.println("머무를 일수를 입력해주세요.");
				int stayDate = sc.nextInt();

				System.out.println("예약이 완료되었습니다.");
				floor.guest[i][j] = new Guest(guestName, guestPhoneNum, true, false, false);
				floor.room[i][j] = new RoomInfo(false, true, true, startDate, startDate.plusDays(stayDate - 1), false);
				System.out.println("예약완료");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				countTry++;
				ms.menuSelect(floor);
			} else {
				System.out.println("예약이 불가능한 방입니다.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
			}
			ms.menuSelect(floor);
		} else {
			if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isPossible() == true) {
				System.out.println("예약할 고객의 정보를 아래에 입력해주세요.");
				System.out.println("이      름: ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연 락 처 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());
				System.out.println("예약하고자 하는 년,월,일을 차례대로 입력해주세요.");
				LocalDate startDate = UtilityHotelFuntion.exceptionDataTest(floor);
				System.out.println("머무를 일수를 입력해주세요.");
				int stayDate = sc.nextInt();

				System.out.println("예약이 완료되었습니다.");
				floor.guest[i][j] = new Guest(guestName, guestPhoneNum, true, false, false);
				floor.room[i][j] = new RoomInfo(false, true, true, startDate, startDate.plusDays(stayDate), false);
				System.out.println("예약완료");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				countTry++;
				ms.menuSelect(floor);
			} else {
				System.out.println("예약이 불가능한 방입니다.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
			}
			ms.menuSelect(floor);

		}
	}

	// 정욱진
	// roomview 에서 층 검색후 체크인
	void checkIn(int selNum, Floor floor) { // ** 여기에서 예약자명단을 호출해 예약된사람인지 먼저체크한후
		// 예약된사람이면 예약한 방호수, 이름, 번호를 물어볼필요없이
		// 객실객체와, 고객객체의 상태를 나타내는 변수값을 변경해주면된다.
		// 변경해준후 예약자명단에서 예약자를 지워주는것
		// 예약자가 아니라면 현장결제이므로 호실,이름,번호를 물어보고 배열에 인스턴스를 추가해준다.
		int i = selNum / 100 - ms.whoFloor(floor);
		int j = selNum % 100 - 1;

		if ((UtilityHotelFuntion.checkRoomNum2(selNum, floor))) { // A동 과 B동을 구분하는 if 문
			if (floor.room[i][j].isCheckIn() == true) { // 현 단계에서 방의 체크인 상태 확인
				System.out.println("이미 체크인 되어 있는 객실입니다.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				ms.menuSelect(floor);
			}
			if (floor.room[i][j].isClean() == false) { // 현 단계에서 방의 청소 상태 확인
				System.out.println("아직 객실 정리가 완료되지 않았습니다. 객실 청소 상태를 확인해주세요.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				ms.menuSelect(floor);
			} /////////////////////////////////////////////////////////////////////// 위쪽은 방에
				/////////////////////////////////////////////////////////////////////// 체크인 하기
				/////////////////////////////////////////////////////////////////////// 전에 조건 확인
			if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isReservation() == false) {
				System.out.println("이름 입력 : ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연락처 입력 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());

				System.out.println("");
				System.out.println();

				LocalDate startDate = LocalDate.now();

				System.out.println("머무를 일수를 입력해주세요.");
				int stayDate = sc.nextInt();

				System.out.println(" 체크인이 완료되었습니다. ");
				System.out.println("첫 화면으로 돌아갑니다.\n");

				floor.guest[i][j] = new Guest(guestName, guestPhoneNum, false, true, true);
				floor.room[i][j] = new RoomInfo(true, false, false, startDate, startDate.plusDays(stayDate), false);
				countTry++; // 메뉴 호출이 반복될때마다 일어나는 씹힘현상 방지용 카운트
				ms.menuSelect(floor);

			} else if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isReservation() == true) { // 이 문항은 예약한
																											// 고객이 체크인
																											// 하고자 할때
				if (floor.room[i][j].getCheckInTime().isAfter(LocalDate.now())) {
					System.out
							.println("현채 체크인 가능일자는 " + floor.room[i][j].getCheckInTime() + "입니다. 가능한 날에 다시 방문부탁드립니다.");
					System.out.println("첫 화면으로 돌아갑니다.\n");
					ms.menuSelect(floor);
				} else if (floor.room[i][j].getCheckInTime().isBefore(LocalDate.now())) {
					System.out.println("체크인을 할 수 있는 날짜가 지났습니다. 체크인이 불가능합니다.");
					System.out.println("첫 화면으로 돌아갑니다.\n");
					ms.menuSelect(floor);
				}
				System.out.println("예약확인을 위해 이름과 연락처를 입력해주세요.");
				System.out.println("이름 입력 : ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연락처 입력 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());

				if ((floor.guest[i][j].getName()).equals(guestName)) {
					System.out.println("체크인이 완료되었습니다.");
					floor.guest[i][j] = new Guest(guestName, guestPhoneNum, false, true, true);
					floor.room[i][j] = new RoomInfo(true, false, false, floor.room[i][j].getCheckInTime(),
							floor.room[i][j].getCheckOutTime(), false);
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else {
				System.out.println("체크인이 불가능한 객실입니다.\n");
				System.out.println("첫 화면으로 돌아갑니다.\n");
			}
			countTry++; // 메뉴 호출이 반복될때마다 일어나는 씹힘현상 방지용 카운트
			ms.menuSelect(floor);
		} else {
			System.out.println("체크인이 불가능한 건물입니다.");
		}
		ms.menuSelect(floor);
	}

	// 정욱진
	// 메인 메뉴에서 체크인 이동
	void checkIn(Floor floor) {
		System.out.println("객실번호를 입력하세요.");
		int checkInNum = UtilityHotelFuntion.exceptionTest(floor);

		int i = (checkInNum / 100) - ms.whoFloor(floor);
		int j = (checkInNum % 100) - 1;
		if ((UtilityHotelFuntion.checkRoomNum2(checkInNum, floor))) {
			if (floor.room[i][j].isCheckIn() == true) {
				System.out.println("이미 체크인 되어 있는 객실입니다.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				ms.menuSelect(floor);
			}
			if (floor.room[i][j].isClean() == false) {
				System.out.println("아직 객실 정리가 완료되지 않았습니다. 객실 청소 상태를 확인해주세요.");
				System.out.println("첫 화면으로 돌아갑니다.\n");
				ms.menuSelect(floor);
			} /////////////////////////////////////////////////////////////////////// 위쪽은 방에
				/////////////////////////////////////////////////////////////////////// 체크인 하기
				/////////////////////////////////////////////////////////////////////// 전에 조건 확인
			if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isReservation() == false) {
				System.out.println("이름 입력 : ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연락처 입력 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());

				LocalDate startDate = LocalDate.now();

				System.out.println("머무를 일수를 입력해주세요.");
				int stayDate = sc.nextInt();

				System.out.println(" 체크인이 완료되었습니다. ");
				System.out.println("첫 화면으로 돌아갑니다.\n");

				floor.guest[i][j] = new Guest(guestName, guestPhoneNum, false, true, true);
				floor.room[i][j] = new RoomInfo(true, false, false, startDate, startDate.plusDays(stayDate), false);
				countTry++;
				ms.menuSelect(floor);

			} else if (floor.room[i][j].isCheckIn() == false && floor.room[i][j].isReservation() == true) {
				if (floor.room[i][j].getCheckInTime().isAfter(LocalDate.now())) {
					System.out
							.println("현채 체크인 가능일자는 " + floor.room[i][j].getCheckInTime() + "입니다. 가능한 날에 다시 방문부탁드립니다.");
					System.out.println("첫 화면으로 돌아갑니다.\n");
					ms.menuSelect(floor);
				} else if (floor.room[i][j].getCheckInTime().isBefore(LocalDate.now())) {
					System.out.println("체크인을 할 수 있는 날짜가 지났습니다. 체크인이 불가능합니다.");
					System.out.println("첫 화면으로 돌아갑니다.\n");
					ms.menuSelect(floor);
				}
				System.out.println("예약확인을 위해 이름과 연락처를 입력해주세요.");
				System.out.println("이름 입력 : ");
				String guestName = sc.nextLine();
				if (countTry > 0) { // 메뉴 호출이 반복될때마다 일어나는 입력 씹힘현상 방지용
					sc.nextLine();
				}
				System.out.println("연락처 입력 : ");
				String guestPhoneNum = UtilityHotelFuntion.phoneOirgin(sc.nextLine());

				if ((floor.guest[i][j].getName()).equals(guestName)) {
					System.out.println("체크인이 완료되었습니다.");
					floor.guest[i][j] = new Guest(guestName, guestPhoneNum, false, true, true);
					floor.room[i][j] = new RoomInfo(true, false, false, floor.room[i][j].getCheckInTime(),
							floor.room[i][j].getCheckOutTime(), false);
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			} else {
				System.out.println("체크인이 불가능한 객실입니다.\n");
				System.out.println("첫 화면으로 돌아갑니다.\n");
			}
			ms.menuSelect(floor);
		} else {
			System.out.println("체크인이 불가능한 건물입니다.");
		}
		countTry++;
		ms.menuSelect(floor);
	}

	// 정욱진
	// 체크아웃
	void checkOut(int selNum, Floor floor) {

		int i = selNum / 100 - ms.whoFloor(floor);
		int j = selNum % 100 - 1;
		if (floor.room[i][j].isCheckIn() == true) {
			floor.guest[i][j] = new Guest();
			floor.room[i][j] = new RoomInfo();
			floor.room[i][j].setClean(false);

			System.out.println(" 체크아웃 되었습니다. ");
			System.out.println("첫 화면으로 돌아갑니다.\n");
		} else {
			System.out.println("체크아웃 불가능한 객실입니다.\n");
			System.out.println("첫 화면으로 돌아갑니다.\n");
		}
		ms.menuSelect(floor);
	}

	// 정욱진
	// 체크아웃
	void checkOut(Floor floor) {
		System.out.println("객실번호를 입력하세요.");
		int checkOutNum = UtilityHotelFuntion.exceptionTest(floor);
		int i = (checkOutNum / 100) - ms.whoFloor(floor);
		int j = (checkOutNum % 100) - 1;

		if (floor.room[i][j].isCheckIn() == false) {
			System.out.println("체크아웃 불가능한 객실입니다.");
			System.out.println("첫 화면으로 돌아갑니다.\n");
			ms.menuSelect(floor);
		} else {
			floor.guest[i][j] = new Guest();
			floor.room[i][j] = new RoomInfo();
			floor.room[i][j].setClean(false);

			System.out.println(" 체크아웃 되었습니다. ");
			System.out.println("첫 화면으로 돌아갑니다.\n");
			ms.menuSelect(floor);
		}
	}

}
