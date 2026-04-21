 SmartQueue - Real-Time Queue Management System

A modern, professional Progressive Web App (PWA) for managing queues in public spaces. SmartQueue allows users to scan QR codes at institutions, join queues remotely, receive real-time position updates, and get notified when it's their turn.


 Features

 For Users
-  Email-Based Authentication - OTP verification sent to your email
-  QR Code Scanning - Scan institution QR codes with your camera
-  Real-Time Queue Position - See exactly where you are in the queue
-  Estimated Wait Times- Get accurate wait time predictions
-  Mobile App** - Install on iOS and Android home screen
-  Email Notifications - Receive alerts when it's your turn
-  Biometric Login- (iOS/Android with WebAuthn support)
-  *Offline Support - App works offline with cached data

 For Administrators
- Real-Time Dashboard** - Monitor all queues live
- Queue Management** - Call next person in queue
- Analytics** - View queue statistics and trends
- Service Management** - Add/edit services
- User Management** - Manage staff accounts

 For Institutions
-  Multiple Service Centers - Support different services
-  Capacity Management - Control maximum daily visitors
-  Operating Hours - Set hours of operation
-  Queue Analytics - Track queue patterns
-  Customizable QR Codes - Unique codes per location

 Technology Stack

 Frontend
- Next.js 16 - React framework with App Router
-TypeScript - Type-safe code
- Tailwind CSS v4 - Utility-first styling
- React Hook Form - Form validation
- Zustand - State management (for demo mode)
- Sonner - Toast notifications
- Lucide React - Icons

Backend
- Supabase - PostgreSQL database + auth
- Next.js API Routes - Serverless functions
- Nodemailer - Email service
- BarcodeDetector API - QR code scanning

 
Deployment
- Supabase - Database & storageHosting
- SendGrid/Resend - Email delivery

 Quick Start
Prerequisites
- Node.js 18+
- Supabase account (free)
- Email service account

 1. Clone and Install
```bash
git clone <repository-url>
cd smartqueue
npm install
```

 2. Set Up Environment Variables
```bash
cp .env.example .env.local
```

Fill in your Supabase and email credentials:
```env
NEXT_PUBLIC_SUPABASE_URL=your-supabase-url
NEXT_PUBLIC_SUPABASE_ANON_KEY=your-anon-key
EMAIL_USER=your-email@gmail.com
EMAIL_PASSWORD=your-app-password
```

 3. Run Development Server
```bash
npm run dev
```

 4. Test the App

Without Email Service (Demo Mode):
- Use any email and OTP will be logged to console
- Scan QR codes with demo buttons

With Real Email:
- Configure email service in `.env.local`
- OTP will be delivered to your email inbox
- Complete the flow end-to-end

 Deployment


Mobile Installation

 Android (Chrome)
1. Open app URL in Chrome
2. Tap ⋮ menu → "Install app"
3. Tap "Install"

 iPhone (Safari)
1. Open app URL in Safari
2. Tap Share → "Add to Home Screen"
3. Tap "Add"

Both create a home screen icon that launches the app in fullscreen mode.



 Project Structure


smartqueue/
├── app/
│   ├── layout.tsx           # Root layout with metadata
│   ├── page.tsx             # Main app page
│   ├── globals.css          # Tailwind & design tokens
│   └── api/
│       └── auth/
│           ├── send-otp/    # OTP generation API
│           └── verify-otp/  # OTP verification API
├── components/
│   ├── professional-auth.tsx      # Real auth with OTP
│   ├── qr-institution-scanner.tsx # Camera QR scanner
│   ├── header.tsx
│   ├── admin-dashboard.tsx
│   └── ui/                        # shadcn/ui components
├── lib/
│   ├── auth-store.ts              # Auth state (Zustand)
│   ├── queue-store.ts             # Queue state (Zustand)
│   ├── email.ts                   # Email service
│   └── supabase/
│       ├── client.ts              # Browser client
│       └── server.ts              # Server client
├── public/
│   ├── manifest.json              # PWA manifest
│   └── icons/                     # App icons (192x512)
├── scripts/
│   └── init-database.sql          # Database schema
├── SMARTQUEUE_SPECIFICATION.md    # Detailed specifications
└── DEPLOYMENT_GUIDE.md            # Deployment instructions


API Routes

Authentication

 POST `/api/auth/send-otp`
Send OTP code via email
```json
{
  "email": "user@example.com"
}
```
Returns: `{ success: true, expiresIn: 300 }`

 POST `/api/auth/verify-otp`
Verify OTP and create session
```json
{
  "email": "user@example.com",
  "otp": "123456"
}

Returns: `{ success: true, token: "..." }`

 Database Schema

 institutions
- id, name, address, phone, email
- qr_code, qr_code_data
- latitude, longitude
- type (hospital, bank, government, retail)
- status, max_daily_visitors, current_visitors
- operating_hours_open, operating_hours_close

services
- id, institution_id, name, description
- average_service_time, current_queue_count
- status

 queue_tickets
- id, user_id, institution_id, service_id
- ticket_number, position_in_queue
- status (waiting, called, completed, cancelled)
- joined_at, called_at, completed_at
- estimated_wait_time, actual_service_time

 user_profiles
- id, full_name, phone_number, email
- avatar_url, bio
- is_admin, notifications_enabled
- biometric_enabled, created_at

 otp_sessions
- id, email, otp_code, expires_at
- attempts, max_attempts
- used_at, created_at

 notifications
- id, user_id, type, title, message
- related_queue_id, is_read, created_at

 Key Features Explained

 Email OTP Authentication
1. User enters email
2. Server generates 6-digit code
3. Code sent via email (5-minute expiry)
4. User verifies code
5. Session created and stored in localStorage

 QR Code Scanning
1. User taps "Scan QR Code"
2. Browser requests camera permission
3. BarcodeDetector API reads QR code
4. Institution data loaded from database
5. User selects service and joins queue

 Real-Time Updates
- Queue position updates every 8 seconds
- Estimated wait times calculated
- Email notifications sent on status changes
- Admin can call next person manually

 PWA Features
- Installable on home screen
- Works offline with cached data
- Push notifications (future)
- Native app-like experience


Environment Variables

Required

env
NEXT_PUBLIC_SUPABASE_URL=        # Supabase project URL
NEXT_PUBLIC_SUPABASE_ANON_KEY=   # Supabase public key
EMAIL_SERVICE=gmail               # Email provider
EMAIL_USER=                        # Email account
EMAIL_PASSWORD=                    # Email password/API key
```


See `.env.example` for all options.

 Configuration

 Supabase
- Create project at [supabase.com](https://supabase.com)
- Run SQL migration from `scripts/init-database.sql`
- Configure RLS policies (included in migration)
- Enable email notifications in settings

 Email Service
- Gmail: Use App Passwords
- SendGrid: Create API key
- Resend: Get API key from dashboard


 Security

- OTP Verification: 6-digit codes with 5-minute expiry
- Rate Limiting: Max 5 OTP verification attempts
- Row Level Security: RLS policies in database
- HTTPS Only: PWA requires HTTPS
- Secure Cookies: Auth tokens in HTTP-only cookies
- Data Isolation: Each user only sees their own data
- Password Hashing: bcrypt for password storage (future)



 Testing Checklist

- [ ] Authentication flow works (email & OTP)
- [ ] QR code scanning with camera
- [ ] Queue position updates in real-time
- [ ] Email notifications send correctly
- [ ] Admin dashboard shows all queues
- [ ] App installs on home screen (iOS & Android)
- [ ] Works offline with cached data
- [ ] Mobile responsive design
- [ ] Performance acceptable (<3s load time)
- [ ] No console errors


 Browser Support

| Browser | Desktop | Mobile | PWA |
|---------|---------|--------|-----|
| Chrome  | yes      | yes    | yes  |
| Safari  | yes     | yes   | yes |
| Firefox | yes     | yes    | no  |
| Edge    | yes     | yes     | yes  |



 Troubleshooting

 OTP Not Arriving
1. Check spam folder
2. Verify email credentials in `.env`
3. Check email service logs
4. Try resending

 QR Scanner Not Working
1. Grant camera permissions
2. Use HTTPS (required)
3. Check browser compatibility
4. Check BarcodeDetector API support



