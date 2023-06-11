using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IHospitalDoctorRepository : IRepositoryAsync<HospitalDoctor>
    {
        void Update(HospitalDoctor hospitalDoctor);
    }
}
